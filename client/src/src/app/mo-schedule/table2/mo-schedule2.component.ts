import { Component, OnInit } from '@angular/core';
import {environment} from "../../../environments/environment";
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import { Scheduleds2 } from "./scheduleds2";
import {ModatabaseService} from "../modatabase.service";
import {Scheduleds4} from "../table4/scheduleds4";


export type MoColumn2 = "lpuId" | "fio" | "dol" | "phone" | "prim" | undefined;//------------------------------------------


@Component({
  selector: 'app-mo-schedule2',
  templateUrl: './mo-schedule2.component.html',
  styleUrls: ['./mo-schedule2.component.css'],
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
export class MoSchedule2Component implements OnInit {
  header_schedule2: string = environment.schedule_mo_header2;
  lpuid: string = environment.kod_mo;
  fio: string = environment.contact_fio;
  dol: string = environment.dol;
  phone: string = environment.phone_number;
  prim: string = environment.prim;



  displayedColumns: MoColumn2[] = [];//------------------------------------------
  dataSource: Scheduleds2 | null;//------------------------------------------


  constructor(public moservice: ModatabaseService) { }

  ngOnInit() {
    this.connect();
  }

  connect(){
    this.displayedColumns = ["lpuId", "fio", "dol", "phone","prim"];
    this.dataSource = new Scheduleds2(this.moservice);//------------------------------------------
    this.moservice.getAll('table2');//-------------------------------------------------
  }

  getNotify(note:string): void{
    this.dataSource.filter = note;
  }

  exportData(){
    this.moservice.exportAsExcelFile(this.dataSource.getDataFilter(),2);
  }

}
