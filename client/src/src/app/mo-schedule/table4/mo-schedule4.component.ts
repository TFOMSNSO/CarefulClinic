import { Component, OnInit } from '@angular/core';
import {environment} from "../../../environments/environment";
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {Scheduleds4} from "./scheduleds4";
import {ModatabaseService, moInfo4} from "../modatabase.service";
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {User} from "../../model/user";
import {DialogTable1Component} from "../table1/dialog-table1/dialog-table1.component";
import {HistoryDialogComponent} from "./history-dialog/history-dialog.component";


export type MoColumn4 = "lpuId" | "address" | "dateBegin" | "dateEnd" | "timeBegin" | "timeEnd" | "typeMo" | "prim" | undefined;


@Component({
  selector: 'app-mo-schedule4',
  templateUrl: './mo-schedule4.component.html',
  styleUrls: ['./mo-schedule4.component.css'],
  providers:[ModatabaseService],
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
export class MoSchedule4Component implements OnInit {
  header_schedule:string = environment.schedule_mo_header4;
  destination_address:string = environment.dest_address;
  dateBegin: string = environment.date_begin;
  dateEnd: string = environment.date_end;
  timeBegin: string = environment.time_begin;
  timeEnd: string = environment.time_end;
  lpuId: string = environment.kod_mo;
  prim: string = environment.prim;
  typeMo: string = environment.type_mo;
  public currentUser: User;
  dataSource:Scheduleds4 | null;//--------------------------------
  displayedColumns : MoColumn4[] = [];//--------------------------------
  dialogRef: MatDialogRef<HistoryDialogComponent> | null;
  selectedDays: string;

  insertHistory: moInfo4[] | null;
  deleteHistory: moInfo4[] | null;
  constructor(public moservice: ModatabaseService,public dialog: MatDialog) { }

  ngOnInit(){

    this.connect();
  }

  connect(){
    this.displayedColumns = ["lpuId", "address", "dateBegin", "dateEnd","timeBegin", "timeEnd","typeMo", "prim"];
    this.dataSource = new Scheduleds4(this.moservice);//------------------------------------
    this.moservice.getAllt4();//-----------------------------------
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.selectedDays = '7';
  }

  getNotify(note:string): void{
    this.dataSource.filter = note;
  }


  getHistory(){
      let cc = {
        disableClose: false,
        panelClass: 'custom-overlay-pane-class',
        hasBackdrop: true,
        backdropClass: '',
        height: '80%',
        width: '80%',
        maxHeight:'80%',
        data: {days:this.selectedDays}
      }
      this.dialogRef = this.dialog.open(HistoryDialogComponent,cc);

  }

  exportData(){
    this.moservice.exportAsExcelFile(this.dataSource.getDataFilter(),4);
  }
}
