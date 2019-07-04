import { Component, OnInit, Input, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatTabChangeEvent} from '@angular/material';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';

import {environment} from "../../../../environments/environment";

@Component({
  selector: 'app-dialogtable3',
  templateUrl: './dialogtable3.component.html',
  styleUrls: ['./dialogtable3.component.css'],
  animations: [
    trigger('toggle', [
      state('true', style({ opacity: 1, color: 'red' })),
      state('void', style({ opacity: 1, color: 'blue' })),
      //transition(':enter', animate('500ms ease-in-out')),
      //transition(':leave', animate('500ms ease-in-out'))
      transition(':enter', [
        style({
          opacity: 0,
          transform: 'translateX(-100%)'
        }),
        animate('1s ease-in')
      ]),
      transition(':leave', [
        animate('0s 0s ease-out', style({
          opacity: 0,
          transform: 'translateX(100%)'
        }))
      ])
    ])
  ]
})
export class Dialogtable3Component{
  monday:string = environment.monday;
  thuesday:string = environment.thuesday;
  wednesday:string = environment.wednesday;
  thursday: string = environment.thursday;
  friday:string = environment.friday;
  saturday:string = environment.saturday;
  sunday:string = environment.sunday;


  kod_mo:string = environment.kod_mo;
  kod_otd: string = environment.kod_otd;
  type_mo: string = environment.type_mo;
  prim: string = environment.prim;
  phone:string = environment.phone_number;
  graphic:string = environment.graphicRab;
  prof:string = environment.prof;
  address:string = environment.address;
  usl:string = environment.usl;
  kab:string = environment.kab;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) { }

}
