import { Component } from '@angular/core';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {environment} from '../../../environments/environment';
import { User } from '../../model/user';
import {ListInformirovanieHeaderService} from './listinformirovanieheader.service';
import { ListExcelFiles } from '../../model/list.files.excel';
import * as FileSaver from 'file-saver';

//зачем тут столько методов хз. не я писал.
@Component({
    selector:'app-informirovanie',
    templateUrl: './listinformirovanieheader.component.html',
  	styleUrls: ['./listinformirovanieheader.component.scss'],
	providers: [ListInformirovanieHeaderService],
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
export class ListInformirovanieHeader{
	 panelOpenState: boolean = false;
	 report_inform_plane_select: string;
	 private currentUser: User;
	 public listExcelFiles: ListExcelFiles[] = [];
	 public listonKvartal: ListExcelFiles[] = [];
	 public listonKvartalActual: ListExcelFiles[] = [];
	 public listInformBrig: ListExcelFiles[] = [];
	 public progress_bar: boolean = false;
	 public panelOpenState1 : boolean = false;
	 public panelOpenState2 : boolean = false;
	 public panelOpenState3 : boolean = false;
	 public panelOpenState4 : boolean = false;
	 public panelOpenstate5: boolean = false;

	  kvs = [
				{value: '1', viewValue: '1-\u0439 \u043A\u0432\u0430\u0440\u0442\u0430\u043B'},//й квартал
				{value: '2', viewValue: '2-\u0439 \u043A\u0432\u0430\u0440\u0442\u0430\u043B'},//й квартал
				{value: '3', viewValue: '3-\u0439 \u043A\u0432\u0430\u0440\u0442\u0430\u043B'},//й квартал
				{value: '4', viewValue: '4-\u0439 \u043A\u0432\u0430\u0440\u0442\u0430\u043B'}//й квартал
			];

   /*list_insur_menu: string = environment.list_insur_menu;
   exit_app: string = environment.exit_app;
   list_inform_header: string  = list_inform_header;*/

   _report_inform: string = environment.report_inform;                                                //Выбранный отчет
   _report_inform_note: string = environment.report_inform_note;                                      //Выберите тип отчета
   _report_inform_plane: string = environment.report_inform_plane;                                    //План информирования за выбранный квартал
   _report_inform_btn_download: string = environment.report_inform_btn_download;                      //Скачать
   _report_inform_all: string = environment.report_inform_all;                                        //Подлежащие информированию за текущий год (п.211 правил)
   _report_inform_note_after: string = environment.report_inform_note_after;                          //Выбранный отчет
   _report_inform_select_kv: string = environment.report_inform_select_kv;                            //Выберите квартал
   _report_reinform: string = environment.report_reinform;                                            //Повторное информирование
   _reestr_inform_all_year_download_file: string = environment.reestr_inform_all_year_download_file;  //Реестр подлежащих информированию  на текущий год
   _reestr_file: string = environment.reestr_file;                                                    //Файл
   _reestr_download: string = environment.reestr_download;                                            //скачать
   _report_inform_about_second_level: string = environment.report_inform_about_second_level;          //Информирование о 2-м этапе
   _profmedosmtr: string = environment.profmedosmtr;                                                  //Профилактические мед.осмотры
   _report_inform_plane_actual = environment.report_inform_plane_actual;                              //Актуальный план информирования за определенный квартал
   _inform_mobile_brig: string = environment.inform_mobile_brig;                                      //Информироване о мобильных бригадах




   constructor(private listInformirovanieHeaderService: ListInformirovanieHeaderService) {
		this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
   }

   init():void{
	 this.getListNameFiles(this.currentUser['role'][0].id);
  }

  init_1():void{
	 this.getListNameFiles_1(this.currentUser['role'][0].id);
  }

	/*  информирование о втором этапе */
   getListNameFiles(data : number): void{
    	 this.listInformirovanieHeaderService.listFiles(data)
	 	 .then(res => {this.listExcelFiles = res});
	}

	/*  Профилактические мед.осмотры */
   getListNameFiles_1(data : number): void{
    	 this.listInformirovanieHeaderService.listFiles_1(data)
	 	 .then(res => {this.listExcelFiles = res});
	}

	init_informirovanie():void{
		this.getListNameFilesInformirovanie(this.currentUser['role'][0].id);
		this.getListNameFilesInformirovanieActual(this.currentUser['role'][0].id);
	}


	init_brig():void{
     this.getListInfromBrig(this.currentUser['role'][0].id);
  }

	getListInfromBrig(data : number): void{
       this.listInformirovanieHeaderService.listFilesInformBrig(data)
         .then(res => this.listInformBrig = res);
  }


	/* поквартальное информирование */
	getListNameFilesInformirovanie(data : number): void{
    	 this.listInformirovanieHeaderService.listFilesKvartals(data)
	 	 .then(res => {this.listonKvartal = res});
	}

	/* Актуальный план информирования за определенный квартал */
	getListNameFilesInformirovanieActual(data : number): void{
    	 this.listInformirovanieHeaderService.listFilesKvartalsActual(data)
	 	 .then(res => {this.listonKvartalActual = res});
	}

	init_reinform():void{
		this.getListNameFilesReinform(this.currentUser['role'][0].id);
	}

	/* повторное информирование */
	getListNameFilesReinform(data : number): void{
    	 this.listInformirovanieHeaderService.listFilesReinform(data)
	 	 .then(res => {this.listExcelFiles = res});
	}




	setTrue(vl : number, vl2 : boolean){
		vl == 1 ? this.panelOpenState1= vl2 : vl == 2 ? this.panelOpenState2= vl2 : vl == 3 ? this.panelOpenState3= vl2 : vl == 4 ? this.panelOpenState4= vl2 : vl == 5 ? this.panelOpenstate5= vl2 : ''
	}

    downloadFile():void{
	this.progress_bar = true;
	this.listInformirovanieHeaderService.downloadFile(this.currentUser['role'][0].id,'inform_for_all_year')
	.then(response =>{
			let blob = new Blob([response.blob()], {type: 'application/x-rar-compressed'});
			FileSaver.saveAs(blob, 'report_allYearInform.rar');
			this.progress_bar = false;
	})
	.catch(e =>{
		console.log('e '+e);
		this.progress_bar = false;
	});
   }

   /**/

    downloadFile_2(data: string,data2: string):void{
      this.progress_bar = true;

      this.listInformirovanieHeaderService.downloadFile_2(data,this.currentUser['role'][0].id,data2)
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

	 downloadFile_informKvartals(data: string):void{
		this.progress_bar = true;

		this.listInformirovanieHeaderService.downloadFile_kvartals(data,this.currentUser['role'][0].id,'inform_about_second_stage')
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

	downloadFile_informKvartalsActual(data: string,data2: string):void{
			this.progress_bar = true;

		this.listInformirovanieHeaderService.downKvartalsActual(data,this.currentUser['role'][0].id,data2)
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

	downloadFile_informReinform(data: string):void{
		this.progress_bar = true;

		this.listInformirovanieHeaderService.downloadFile_reinform(data,this.currentUser['role'][0].id,'reinform')
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

	downloadFile_InformBrig(data: string){
    this.progress_bar = true;

    this.listInformirovanieHeaderService.downloadFile_informBrig(data,this.currentUser['role'][0].id,'inform_brig')
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




}
