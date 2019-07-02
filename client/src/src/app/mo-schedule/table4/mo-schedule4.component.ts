import { Component, OnInit } from '@angular/core';
import {environment} from "../../../environments/environment";
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {Schdeuleds4} from "./schdeuleds4";
import {ModatabaseService} from "../modatabase.service";



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


  dataSource:Schdeuleds4 | null;
  displayedColumns : MoColumn4[] = [];

  constructor(public moservice: ModatabaseService) { }

  ngOnInit() {
    this.connect();
  }

  connect(){
    this.displayedColumns = ["lpuId", "address", "dateBegin", "dateEnd","timeBegin", "timeEnd","typeMo", "prim"];
    this.dataSource = new Schdeuleds4(this.moservice);
    this.moservice.getAllt4();
  }

  getNotify(note:string): void{
    this.dataSource.filter = note;
  }

}
