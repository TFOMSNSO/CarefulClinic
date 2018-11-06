import { Component, OnInit } from '@angular/core';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import { User } from '../../../model/user';
import { ReportStatInformService } from './report.stat.inform.service';
import * as FileSaver from 'file-saver';
//import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
//import {IMyDpOptions} from 'mydatepicker';
import { ListExcelFiles } from '../../../model/list.files.excel';
import {environment} from '../../../../environments/environment';
//import {ListInformirovanieHeaderService} from "../../list-informirovanie/listinformirovanieheader.service";




@Component({
    selector:'modul-report-stat-inform',
    templateUrl: './report.stat.inform.component.html',
  	styleUrls: ['./report.stat.inform.component.scss'],
	providers: [ReportStatInformService],
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
export class ReportStatisticsInformirovanieComponent {

	 //panelOpenState: boolean = false;
	 //report_inform_plane_select: string;
	 private currentUser: User;
	 public listExcelFiles: ListExcelFiles[] = [];
	 //public filesExpertise: ListExcelFiles[] = [];
	 public progress_bar: boolean = false;
	 public panelOpenState1 : boolean = false;
	 public panelOpenState2 : boolean = false;
	 public panelOpenState3 : boolean = false;
   public panelOpenState4 : boolean = false;
   public panelOpenState5 : boolean = false;
	// public myForm: FormGroup;

	 _report_stat_inform: string = environment.report_stat_inform;                   //Сведения об организации информирования застрахованных лиц о возможности прохождения диспансеризации
	 _3b_expertise: string = environment.expertise_field2;                           //Контроль МКБ в 1 и 3б группах здоровья
	 _3a_3b_other: string = environment.a3_3b_other;                                 //Контроль 3 группы на назначение лечебно-профилактических мероприятий
	 _report_inform_note: string = environment.report_inform_note;                   //Выберите тип отчета
	 _report_inform_note_after: string = environment.report_inform_note_after;       //Выбранный отчет
	 _list_resolved: string = environment.list_resolved;                             //Сформированные отчеты
	 _expertise_field1_1: string = environment.expertise_field1_1;                   //Выберите временной период по счетам пролеченных
	 _field_is_required: string = environment.field_is_required;                     //поле обязательно для заполнения
	 _reset: string = environment.reset;                                             //сбросить
  _reestr_download: string = environment.reestr_download;
  _table3: string = environment.table3;
  _table4: string = environment.table4;
  _table5: string = environment.table5;
  _table6: string = environment.table6;

  constructor(private reportStatInformService: ReportStatInformService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  setTrue(vl : number, vl2 : boolean){
    vl == 1 ? this.panelOpenState1= vl2 : vl == 2 ? this.panelOpenState2= vl2 : vl == 3 ? this.panelOpenState3= vl2 : vl == 4 ? this.panelOpenState4= vl2 : ''
  }

  init_table3():void{
    this.getListNameFilesTable3(this.currentUser['role'][0].id);
  }

  init_table4():void{
    this.getListNameFilesTable4(this.currentUser['role'][0].id);
  }

  init_table5():void{
    this.getListNameFilesTable5(this.currentUser['role'][0].id);
  }

  init_table6():void{
    this.getListNameFilesTable6(this.currentUser['role'][0].id);
  }

  getListNameFilesTable3(data : number): void{
    this.reportStatInformService.listFilesTable3(data)
      .then(res => {this.listExcelFiles = res});
  }

  getListNameFilesTable4(data : number): void{
    this.reportStatInformService.listFilesTable4(data)
      .then(res => {this.listExcelFiles = res});
  }

  getListNameFilesTable5(data : number): void{
    this.reportStatInformService.listFilesTable5(data)
      .then(res => {this.listExcelFiles = res});
  }

  getListNameFilesTable6(data : number): void{
    this.reportStatInformService.listFilesTable6(data)
      .then(res => {this.listExcelFiles = res});
  }



  downloadFile_table3(data: string):void{
    this.progress_bar = true;

    this.reportStatInformService.downloadFile_table3(data,this.currentUser['role'][0].id,'table3')
      .then(result =>{
        let blob = new Blob([result.blob()], {type: 'application/x-rar-compressed'});
        FileSaver.saveAs(blob, data);
        this.progress_bar = false;
      })
      .catch(e =>{
        this.progress_bar = false;
        console.log('e '+e);
      });
  }

  downloadFile_table4(data: string):void{
    this.progress_bar = true;

    this.reportStatInformService.downloadFile_table4(data,this.currentUser['role'][0].id,'table4')
      .then(result =>{
        let blob = new Blob([result.blob()], {type: 'application/x-rar-compressed'});
        FileSaver.saveAs(blob, data);
        this.progress_bar = false;
      })
      .catch(e =>{
        this.progress_bar = false;
        console.log('e '+e);
      });
  }

  downloadFile_table5(data: string):void{
    this.progress_bar = true;

    this.reportStatInformService.downloadFile_table5(data,this.currentUser['role'][0].id,'table5')
      .then(result =>{
        let blob = new Blob([result.blob()], {type: 'application/x-rar-compressed'});
        FileSaver.saveAs(blob, data);
        this.progress_bar = false;
      })
      .catch(e =>{
        this.progress_bar = false;
        console.log('e '+e);
      });
  }

  downloadFile_table6(data: string):void{
    this.progress_bar = true;

    this.reportStatInformService.downloadFile_table6(data,this.currentUser['role'][0].id,'table6')
      .then(result =>{
        let blob = new Blob([result.blob()], {type: 'application/x-rar-compressed'});
        FileSaver.saveAs(blob, data);
        this.progress_bar = false;
      })
      .catch(e =>{
        this.progress_bar = false;
        console.log('e '+e);
      });
  }




/*
	ngOnInit() {

	    this.myForm =  this.formBuilder.group({
	      date1: ['', Validators.required],
	      date2: ['', Validators.required]
		});

		//this.init_Expertise();
	}*/

/*	myDatePickerOptions: IMyDpOptions = {
        // other options...
        dateFormat: 'dd.mm.yyyy'

    };*/


  /* constructor(private reportService: ReportStatExpertiseService,private formBuilder: FormBuilder) {
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
   }



   getListNameFiles(data : number): void{
    	// this.listInformirovanieHeaderService.listFiles(data)
	 	// .then(res => {this.listExcelFiles = res});
	}*/



/*
	downloadFile_expertiseReport(form: any){
		this.progress_bar = true;
		this.reportService.downloadFile_expertise(form.value.date1.formatted,form.value.date2.formatted,this.currentUser['role'][0].id)
		.then(result =>{
			this.init_Expertise();
			this.progress_bar = false;

		})
	}


	downloadFile_expertiseReport_3b(form: any){
		this.progress_bar = true;
		this.reportService.downloadFile_expertise3b(form.value.date1.formatted,form.value.date2.formatted,this.currentUser['role'][0].id)
		.then(result =>{
			this.init_Expertise();
			this.progress_bar = false;

		})
	}

	downloadFile_expertiseReport_3a3b(form: any){
		this.progress_bar = true;
		this.reportService.downloadFile_expertise3a3b(form.value.date1.formatted,form.value.date2.formatted,this.currentUser['role'][0].id)
		.then(result =>{
			this.init_Expertise();
			this.progress_bar = false;

		})
	}

	resetForm() {
  	  this.myForm.reset();

	}


	init_Expertise():void{
		this.getListNameFilesExpertise(this.currentUser['role'][0].id);
	}

	getListNameFilesExpertise(data : number): void{
    	 this.reportService.listFilesExpertise(data)
	 	 .then(res => {this.filesExpertise = res});
	}


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

	}*/

}
