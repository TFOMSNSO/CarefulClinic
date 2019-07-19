import { Component, OnInit } from '@angular/core';
import { MoColumn4 } from "../../table4/mo-schedule4.component";
import {ModatabaseService} from "../../modatabase.service";
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {environment} from "../../../../environments/environment";
import {Scheduleds5} from "../scheduleds5";

@Component({
  selector: 'app-mo-schedule5',
  templateUrl: './mo-schedule5.component.html',
  styleUrls: ['./mo-schedule5.component.css'],
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
export class MoSchedule5Component implements OnInit {
  header_schedule:string = environment.schedule_mo_header5;
  destination_address:string = environment.nas_punkt;
  dateBegin: string = environment.date_begin;
  dateEnd: string = environment.date_end;
  timeBegin: string = environment.time_sbor;
  timeEnd: string = environment.time_back;
  lpuId: string = environment.kod_mo;
  prim: string = environment.prim;
  typeMo: string = environment.type_mo;

  dataSource :Scheduleds5 | null;

  displayedColumns : MoColumn4[] = [];//--------------------------------

  constructor(public moservice: ModatabaseService) { }

  ngOnInit() {
    this.connect();
  }


  connect(){
    this.displayedColumns = ["lpuId", "address", "dateBegin", "dateEnd","timeBegin", "timeEnd","typeMo", "prim"];
    this.dataSource = new Scheduleds5(this.moservice);//------------------------------------
    this.moservice.getAllt5();//-----------------------------------
  }


  getNotify(note:string): void{
    this.dataSource.filter = note;
  }

  exportData(){
    this.moservice.exportAsExcelFile(this.dataSource.getDataFilter(),5);
  }
}
