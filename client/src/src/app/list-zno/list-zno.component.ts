import { Component, OnInit, Inject, ViewChild, TemplateRef,ElementRef } from '@angular/core';
import {PeopleDatabase, UserData} from '../list-prophylactic/people-database';
import {ProphylacticDataSource} from '../list-prophylactic/data-source';
import {MatPaginator} from '@angular/material';
import {MatSort} from '@angular/material';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import { DialogComponent } from '../list-prophylactic/dialog.component';
import {DOCUMENT} from '@angular/platform-browser';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/observable/fromEvent';
import {MatSnackBar} from '@angular/material';
import {environment} from '../../environments/environment';


export type UserProperties = 'personSurname' | 'personKindfirstname' | 'personKindlastname' | 'personBirthday' | 'personYears' | 'edit' | undefined;


@Component({
  selector: 'app-list-zno',
  templateUrl: './list-zno.component.html',
  styleUrls: ['./list-zno.component.css'],
  animations:[
    trigger('questionsAnim', [
      transition('* => *', [
        query('.full-width,.test', style({ opacity: 0 }), {optional: true}),

        query('.full-width,.test', stagger('1400ms', [
          animate('1s ease-in', keyframes([
            style({opacity: 0, transform: 'translateY(-100%)', offset: 0}),
            style({opacity: 0, transform: 'translateY(500px)',  offset: 0.3}),
            style({opacity: 1, transform: 'translateY(0)',     offset: 1.0}),
          ]))]), {optional: true}),

      ])

    ]),
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
export class ListZnoComponent implements OnInit {
  _searchFIOD: string = environment.searchFIOD;
  _searchKEYS: string = environment.searchKEYS;
  _exportExcel: string = environment.exportExcel;
  _cleartable: string = environment.cleartable;
  _downloadtask: string = environment.downloadtask;
  /*_uploadfile: string = environment.uploadfile;*/
  t_years: string = environment.t_years;
  add_table = environment.add_table;
  list_zno: string = environment.title_zno;
  surname: string = environment.surname;
  firstname: string = environment.firstname;
  lastname: string = environment.lastname;
  bithday: string = environment.bithday;
  bad_action_add_person = environment.bad_action_add_person;
  action_add_person = environment.action_add_person;
  progress_bar: boolean = false;
  dataSource: ProphylacticDataSource | null;
  displayedColumns: UserProperties[] = [];

  dialogRef: MatDialogRef<DialogComponent> | null;

  constructor(public _peopleDatabase: PeopleDatabase,
              public snackBar: MatSnackBar,public dialog: MatDialog) { }

  ngOnInit() {
    this.connect();
  }



  connect() {
    this.displayedColumns = ['personSurname','personKindfirstname','personKindlastname','personBirthday','personYears','edit'];
    this.dataSource = new ProphylacticDataSource(this._peopleDatabase);
    this._peopleDatabase.initialize();
  }

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

  getNotify(note:string): void{
    this.dataSource.filter = note;
  }

  isValidBtnExport(): boolean{
    if(this.dataSource._peopleDatabase.data.length > 0)
          { return true;}
      else{ return false;}

  }

  get():void{
    this._peopleDatabase.exportToExcel(this.dataSource._peopleDatabase.data);
  }

  preview(pr:any):void{
    console.log('preview:'+pr.personSurname);
    let cc = {
      disableClose: true,
      panelClass: 'custom-overlay-pane-class',
      hasBackdrop: true,
      backdropClass: '',
      width: '70%',
      height: '500px',
      position: {
        top: '',
        bottom: '',
        left: '',
        right: ''
      },
      data: pr
    }
    this.dialogRef = this.dialog.open(DialogComponent,cc);
    // dialogRef.afterClosed().subscribe(result => {
    // this.selectedOption = result;
    //});

    let result = JSON.stringify(pr, function(key, val) {
      if (key !== "edit")
        return val;
    });

  }

  cleartable():void{
    //this.dataSource._peopleDatabase.data.next([]);
    this._peopleDatabase.initialize();
  }
}
