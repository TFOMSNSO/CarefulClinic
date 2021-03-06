import { Component, OnInit } from '@angular/core';
import {environment} from "../../../environments/environment";
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {Scheduleds} from "./scheduleds";
import {ModatabaseService, moInfo} from "../modatabase.service";
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {DialogTable1Component} from "./dialog-table1/dialog-table1.component";
import {Historydialog1Component} from "./historydialog1/historydialog1.component";

export type MoColumn = "lpuId" | "otdId" | "address" | "phone" | "typeMo" | "prof" | "prim" | "about" | undefined;



@Component({
  selector: 'app-mo-schedule',
  templateUrl: './mo-schedule.component.html',
  styleUrls: ['./mo-schedule.component.css'],
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
export class MoScheduleComponent implements OnInit {
  title_schedule:string = environment.menu_schedule_mo;
  header_schedule:string = environment.schedule_mo_header;
  lpuid: string = environment.kod_mo;
  kodOtd: string = environment.kod_otd;
  phone: string = environment.phone_number;
  typeMo: string = environment.type_mo;
  otkProf: string = environment.prof;
  address: string = environment.address;
  prim: string = environment.prim;
  about: string = environment.about;
  selectedDays: string;

  dataSource:Scheduleds | null;
  displayedColumns : MoColumn[] = [];
  dialogRef: MatDialogRef<DialogTable1Component> | null;
  dialogHis: MatDialogRef<Historydialog1Component> | null;

  constructor(public moservice: ModatabaseService,public dialog: MatDialog) { }

  ngOnInit(){
    this.connect();
  }

  connect(){
    this.displayedColumns = ["lpuId", "otdId", "address", "phone", "typeMo", "prof", "about"];
    this.dataSource = new Scheduleds(this.moservice);

    this.moservice.getAll('table1');
    this.selectedDays = '7';
  }

  preview(row:any){
    let cc ={
      disableClose: false,
      panelClass: 'custom-overlay-pane-class',
      hasBackdrop: true,
      backdropClass: '',
      width: '70%',
      //height:'70%',
      data: row
    }

    this.dialogRef = this.dialog.open(DialogTable1Component,cc);
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
    this.dialogHis = this.dialog.open(Historydialog1Component,cc);
  }

  exportData(){
    this.moservice.exportAsExcelFile(this.dataSource.getDataFilter(),1);
  }

  getNotify(note:string): void{
    this.dataSource.filter = note;
  }

}
