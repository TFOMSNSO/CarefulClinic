import { Component, OnInit } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {ModatabaseService} from "../../modatabase.service";
import {Scheduleds3} from "../scheduleds3";
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {Dialogtable3Component} from "../dialogtable3/dialogtable3.component";



export type MoColumn3 = "lpuId" | "kodOtd" | "usl" | "kab" | "typeMo" | "prim" | "about" | undefined;


@Component({
  selector: 'app-mo-schedule3',
  templateUrl: './mo-schedule3.component.html',
  styleUrls: ['./mo-schedule3.component.css'],
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


export class MoSchedule3Component implements OnInit {
  header_schedule:string = environment.schedule_mo_header3;
  kodOtd: string = environment.kod_otd;
  usl: string = environment.usl;
  kab: string = environment.kab;
  lpuId: string = environment.kod_mo;
  prim: string = environment.prim;
  typeMo: string = environment.type_mo;
  about: string = environment.about;



  dataSource:Scheduleds3 | null;//--------------------------------
  displayedColumns : MoColumn3[] = [];//--------------------------------
  dialogRef: MatDialogRef<Dialogtable3Component> | null;



  constructor(public moservice: ModatabaseService,public dialog: MatDialog) { }

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
    console.log(JSON.stringify(row));
    this.dialogRef = this.dialog.open(Dialogtable3Component,cc);
  }



  ngOnInit() {
    this.connect();
  }

  connect(){
    this.displayedColumns = ["lpuId", "kodOtd", "usl", "kab", "typeMo", "prim", "about"];
    this.dataSource = new Scheduleds3(this.moservice);//------------------------------------
    this.moservice.getAllt3();//-----------------------------------
  }

  getNotify(note:string): void{
    this.dataSource.filter = note;
  }

  get(){
    this.moservice.exportExcel3(this.dataSource.getDataFilter());//-------------------------------------------
  }

}
