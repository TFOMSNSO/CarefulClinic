import { Component } from '@angular/core';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {environment} from '../../environments/environment';
import {PeopleDatabase} from "../list-prophylactic/people-database";
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {MatSnackBar} from '@angular/material';

@Component({
    selector:'app-header',
    templateUrl: './header.component.html',
  	styleUrls: ['./header.component.scss'],
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
export class HeaderComponent{
   list_insur_menu: string = environment.list_insur_menu;
   exit_app: string = environment.exit_app;
   list_inform_header: string  = environment.list_inform_header;//Списки для информирования
   statistics_inform_header: string  = environment.statistics_inform_header;
   statistics_assent_header: string  = environment.statistics_assent_header;
   telephone_surveys: string = environment.telephone_surveys;

   _menu_main_page:string  = environment.menu_main_page;
   _menu_report_page:string  = environment.menu_report_page;
   _menu_admin:string  = environment.menu_admin;
   _menu_admin_inform_allyear:string  = environment.menu_admin_inform_allyear;
   _sp3_menu: string = environment.sp3_menu;
   _sp3_menu_expertisa: string = environment.sp3_menu_expertisa;
   _sp3_menu_d_reestr: string = environment.sp3_menu_d_reestr;
   zno_item: string = environment.title_zno;
  _uploadfile: string = environment.uploadfile;
  schedule:string = environment.menu_schedule_mo;

  action_add_person = environment.action_add_person;
  add_table = environment.add_table;
  bad_action_add_person = environment.bad_action_add_person;

  progress_bar: boolean = false;

  constructor(public _peopleDatabase: PeopleDatabase,
              public dialog: MatDialog,
              public snackBar: MatSnackBar) { }



    public LOGO = require("./logo_small.png");

  /*выпадающее окно с информацией о загруженных данных*/
  /*todo разобраться с кодом и убрать лишнее*/
  handleProgressUpdated($event):void{
    //  off progress bar
    this.progress_bar = JSON.parse($event.note);

    if($event.search_keys){	this.action_add_person = '\u0417\u0430\u0441\u0442\u0440\u0430\u0445\u043E\u0432\u0430\u043D\u043D\u044B\u0435 \u043B\u0438\u0446\u0430 \u043D\u0430\u0439\u0434\u0435\u043D\u044B \u0432 \u0431\u0430\u0437\u0435 \u0420\u0421 \u0415\u0420\u0417';	}

    // false - ���� �������� �������� ����; 0/1 - ��������� ������ � �� ���
    if($event.note === 'false' && $event.result !== 0){
      this.snackBar.open(this.action_add_person,this.add_table, {
        duration: 10000,
      });
    }
    if($event.note === 'false' && $event.result === 0){
      this.snackBar.open(this.bad_action_add_person,undefined, {
        duration: 10000,
      });
    }
    if($event.note === 'false' && $event.result === -1){
      this.snackBar.open('Call the admin web site','Error on server side', {
        duration: 10000,
      });
    }
    if($event.note === 'false' && $event.result === 200){
      let tmp = $event.status;
      this.snackBar.open(tmp, '', {
        duration: 10000,
      });
    }
    if($event.note === 'false' && $event.result === 404){
      let tmp = $event.status;
      this.snackBar.open('', tmp, {
        duration: 10000,
      });
    }
  }
}
