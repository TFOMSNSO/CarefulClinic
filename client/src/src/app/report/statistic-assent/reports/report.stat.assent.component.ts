import { Component, OnInit } from '@angular/core';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import { User } from '../../../model/user';
import { ReportStatAssentService } from './report.stat.assent.service';
//import { ListExcelFiles } from '.../../model/list.files.excel';
import * as FileSaver from 'file-saver';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import {IMyDpOptions} from 'mydatepicker';
import { ListExcelFiles } from '../../../model/list.files.excel';
import {environment} from '../../../../environments/environment';




@Component({
    selector:'modul-report-stat-assent',
    templateUrl: './report.stat.assent.component.html',
  	styleUrls: ['./report.stat.assent.component.scss'],
	providers: [ReportStatAssentService],
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
export class ReportStatisticsAssentComponent implements OnInit{

	 panelOpenState: boolean = false;
	 report_inform_plane_select: string;
	 private currentUser: User;
	 //public listExcelFiles: ListExcelFiles[] = [];
	 public filesAssent: ListExcelFiles[] = [];
	 public progress_bar: boolean = false;
	 public panelOpenState1 : boolean = false;
	 public panelOpenState2 : boolean = false;
	 public panelOpenState3 : boolean = false;
	 public myForm: FormGroup;
	 
	 _report_stat_assent: string = environment.report_stat_assent;
	 _3b_expertise: string = environment.expertise_field2;
	 _3a_3b_other: string = environment.a3_3b_other;
	 _report_inform_note: string = environment.report_inform_note; 
	 _report_inform_note_after: string = environment.report_inform_note_after;
	 _list_resolved: string = environment.list_resolved;
	 _expertise_field1_1: string = environment.expertise_field1_1;
	 _assent_field1_1: string = environment.assent_field1_1;
	 _field_is_required: string = environment.field_is_required;
	 _reset: string = environment.reset;
	 data_not_found = environment.data_not_found;
	 
	 
	
   
  
	ngOnInit() {
	
	    this.myForm =  this.formBuilder.group({
	      date1: ['', Validators.required],
	      date2: ['', Validators.required]
		});
		
		this.init_list_Assent();
	}
	
	myDatePickerOptions: IMyDpOptions = {
        // other options...
        dateFormat: 'dd.mm.yyyy'
        
    };
   
   
   constructor(private reportService: ReportStatAssentService,private formBuilder: FormBuilder) { 
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
   }
   
   
  
   getListNameFiles(data : number): void{
    	// this.listInformirovanieHeaderService.listFiles(data)
	 	// .then(res => {this.listExcelFiles = res});
	}
    
	setTrue(vl : number, vl2 : boolean){
		vl == 1 ? this.panelOpenState1= vl2 : vl == 2 ? this.panelOpenState2= vl2 : vl == 3 ? this.panelOpenState3= vl2 : ''
	}
	
	
	downloadFile_assentReport(form: any){
		this.progress_bar = true;
		this.reportService.downloadFile_assent(form.value.date1.formatted,form.value.date2.formatted,this.currentUser['role'][0].id)
		.then(result =>{
			this.init_list_Assent();
			this.progress_bar = false;
			
		})
	}
	
	
	downloadFile_expertiseReport_3b(form: any){
		this.progress_bar = true;
		this.reportService.downloadFile_expertise3b(form.value.date1.formatted,form.value.date2.formatted,this.currentUser['role'][0].id)
		.then(result =>{
			this.init_list_Assent();
			this.progress_bar = false;
			
		})
	}
	
	downloadFile_expertiseReport_3a3b(form: any){
		this.progress_bar = true;
		this.reportService.downloadFile_expertise3a3b(form.value.date1.formatted,form.value.date2.formatted,this.currentUser['role'][0].id)
		.then(result =>{
			this.init_list_Assent();
			this.progress_bar = false;
			
		})
	}
	
	resetForm() {
  	  this.myForm.reset(); 
  	  
	}
	
	
	init_list_Assent():void{
		this.getListNameFilesAssent(this.currentUser['role'][0].id);
	}
	
	getListNameFilesAssent(data : number): void{
    	 this.reportService.listFilesAssent(data)
	 	 .then(res => {this.filesAssent = res});
	}
	
	/* Загрузка файла из списка отчетов Assent */
	
	downloadFile(data: string):void{
	this.progress_bar = true;
	
	this.reportService.downloadFile(data,this.currentUser['role'][0].id)
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
 
}