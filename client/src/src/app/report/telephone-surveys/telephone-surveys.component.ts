import { Component } from '@angular/core';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {environment} from '../../../environments/environment';
import { User } from '../../model/user';
import { TelephoneSurveysService } from './telephone-surveys.service';
import { ListExcelFiles } from '../../model/list.files.excel';
import * as FileSaver from 'file-saver';






@Component({
  selector:'modul-telephone-surveys',
  templateUrl: './telephone-surveys.component.html',
  styleUrls: ['./telephone-surveys.component.scss'],
  providers: [TelephoneSurveysService],
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

export class TelephoneSurveysComponent {

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
  _flk1: string = environment.flk1;
  _flk2: string = environment.flk2;
  _flk3: string = environment.flk3;
  _flk4: string = environment.flk4;

  constructor(private reportStatInformService: TelephoneSurveysService) {
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
}
