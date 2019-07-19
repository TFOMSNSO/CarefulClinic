import { Component, OnInit } from '@angular/core';
import {environment} from "../../../../environments/environment";
import {ModatabaseService} from "../../modatabase.service";
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {Scheduleds6} from "../scheduleds6";


export type MoColumn6 = "lpuId" | "kodOtd" | "kab" | "date" | "timeBegin" | "timeEnd" | "typeMo" | "prim" | undefined;


@Component({
  selector: 'app-mo-schedule6',
  templateUrl: './mo-schedule6.component.html',
  styleUrls: ['./mo-schedule6.component.css'],
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
export class MoSchedule6Component implements OnInit {
  header_schedule:string = environment.schedule_mo_header6;
  kodOtd: string = environment.kod_otd;
  kab: string = environment.kab;
  date:string = environment.date;
  timeBegin: string = environment.time_begin;
  timeEnd: string = environment.time_end;
  lpuId: string = environment.kod_mo;
  prim: string = environment.prim;
  typeMo: string = environment.type_mo;

  dataSource:Scheduleds6 | null;//--------------------------------
  displayedColumns : MoColumn6[] = [];//--------------------------------

  constructor(public moservice: ModatabaseService) { }

  ngOnInit() {
    this.connect();
  }

  connect(){
    this.displayedColumns = ["lpuId", "kodOtd", "kab", "date", "timeBegin", "timeEnd", "typeMo", "prim" ];
    this.dataSource = new Scheduleds6(this.moservice);//------------------------------------
    this.moservice.getAllt6();//-----------------------------------
  }

  getNotify(note:string): void{
    this.dataSource.filter = note;
  }

  exportData(){
    this.moservice.exportAsExcelFile(this.dataSource.getDataFilter(),6);
  }

}
