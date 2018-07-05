import { Component, OnInit } from '@angular/core';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import { User } from '../../../model/user';
import { InfoDReestrService } from './info.d.reestr.service';
//import { ListExcelFiles } from '.../../model/list.files.excel';
import * as FileSaver from 'file-saver';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import {IMyDpOptions} from 'mydatepicker';
import { ListExcelFiles } from '../../../model/list.files.excel';
import {environment} from '../../../../environments/environment';




@Component({
    selector:'app-info-dreestr',
    templateUrl: './info.d.reestr.component.html',
  	styleUrls: ['./info.d.reestr.component.scss'],
	providers: [InfoDReestrService],
	animations: [
	trigger('flyInOut', [
            state('in', style({ opacity: 1, transform: 'translateX(0)' })),
            transition('void => *', [
                style({
                    opacity: 0,
                    transform: 'translateX(-100%)'
                }),
                animate('0.6s ease-in')
            ]),
            transition('* => void', [
                animate('0.2s 0.1s ease-out', style({
                    opacity: 0,
                    transform: 'translateX(100%)'
                }))
            ])
    ])
    
    ]
})
export class InfoDReestrComponent implements OnInit{

	 public panelOpenState1 : boolean = false;
	 public listExcelFiles: ListExcelFiles[] = [];
	 public allfile: ListExcelFiles[] = [];
	 private currentUser: User;
	 public progress_bar: boolean = false;
	 public panelOpenState2 : boolean = false;
	 public myForm: FormGroup;
	 
	 _reset: string = environment.reset;
	 _info_d_reestr1: string = environment.info_d_reestr1;
	 _info_d_reestr: string = environment.info_d_reestr;
	 _report_inform_note_after: string = environment.report_inform_note_after;
	 _report_inform_note: string = environment.report_inform_note;
	 _reestr_file: string = environment.reestr_file;
	 _reestr_download: string = environment.reestr_download;
	 
	
	ngOnInit() {
			
			 this.myForm =  this.formBuilder.group({
			  date1: ['', Validators.required],
			  date2: ['', Validators.required]
			});
		
		this.init_ListD_reestr();
	}
	
	myDatePickerOptions: IMyDpOptions = {
        // other options...
        dateFormat: 'dd.mm.yyyy'
        
    };
	
	
	constructor(private infoDReestrService: InfoDReestrService,private formBuilder: FormBuilder) { 
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
   }
   
	setTrue(vl : number, vl2 : boolean){
		
		vl == 1 ? this.panelOpenState1= vl2 : vl == 2 ? this.panelOpenState2= vl2 : false;
	}  
	
	init_1():void{
	 this.getListNameFiles_1(this.currentUser['role'][0].id);
	}
	
	
	resetForm() {
  	  this.myForm.reset(); 
  	  
	}
	
	init_ListD_reestr():void{
		this.getListNameFilesD_reestr(this.currentUser['role'][0].id);
	}
	
	downloadFile_D_reestr_Report(form: any){
		this.progress_bar = true;
		this.infoDReestrService.downloadFile_d_reestr(form.value.date1.formatted,form.value.date2.formatted,this.currentUser['role'][0].id)
		.then(result =>{
			this.init_ListD_reestr();
			this.progress_bar = false;
			
		})
	}
	
	
	
	getListNameFilesD_reestr(data : number): void{
    	 this.infoDReestrService.listFilesD_reestr(data)
	 	 .then(res => {this.allfile = res});
	}
	
	
	
	
	/*  Информирование д-учетников*/
	getListNameFiles_1(data : number): void{
    	 this.infoDReestrService.listFiles_1(data)
	 	 .then(res => {this.listExcelFiles = res});
	}
	
	/* Скачать файл д учета*/
	
	downloadFile_2(data: string,data2: string):void{
	this.progress_bar = true;
	
	this.infoDReestrService.downloadFile_2(data,this.currentUser['role'][0].id,data2)
	.then(result =>{
			let blob = new Blob([result.blob()], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
			FileSaver.saveAs(blob, data);
			this.progress_bar = false;
	})
	.catch(e =>{
		this.progress_bar = false;
		console.log('e '+e);
	});
	
	}
	
	
	downloadFile(data: string):void{
	this.progress_bar = true;
	
	this.infoDReestrService.downloadFile(data,this.currentUser['role'][0].id)
	.then(result =>{
			let blob = new Blob([result.blob()], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
			FileSaver.saveAs(blob, data);
			this.progress_bar = false;
	})
	.catch(e =>{
		this.progress_bar = false;
		
	});
	
	}
  
   
 
}