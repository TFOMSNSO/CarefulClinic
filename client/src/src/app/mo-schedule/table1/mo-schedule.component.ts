import { Component, OnInit } from '@angular/core';
import {environment} from "../../../environments/environment";
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {Schdeuleds} from "./schdeuleds";
import {ModatabaseService} from "../modatabase.service";
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {DialogTable1Component} from "./dialog-table1/dialog-table1.component";

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

  dataSource:Schdeuleds | null;
  displayedColumns : MoColumn[] = [];
  dialogRef: MatDialogRef<DialogTable1Component> | null;

  constructor(public moservice: ModatabaseService,public dialog: MatDialog) { }

  ngOnInit() {
    this.connect();
  }

  connect(){
    this.displayedColumns = ["lpuId", "otdId", "address", "phone", "typeMo", "prof", "about"];
    this.dataSource = new Schdeuleds(this.moservice);
  }

  preview(row:any){
    let cc ={
      disableClose: false,
      panelClass: 'custom-overlay-pane-class',
      hasBackdrop: true,
      backdropClass: '',
      width: '70%',
      height:'70%',
      data: row
    }
    //console.log('preview:' + JSON.stringify(row));

    this.dialogRef = this.dialog.open(DialogTable1Component,cc);
  }

  getNotify(note:string): void{
    this.dataSource.filter = note;
  }

}
