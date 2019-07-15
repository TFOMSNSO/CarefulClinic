import { Component, Inject, OnInit} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatTabChangeEvent} from '@angular/material';
import {environment} from "../../../../environments/environment";
import { moInfo4 } from "../../modatabase.service";

export class disp_table4{
  lpuId: string;
  address: string;
  dateInsert: string;
  dateBegin: string;
  dateEnd: string;
  timeBegin: string;
  timeEnd: string;
  typeMo: string;
  prim: string;
  id: string;
}

@Component({
  selector: 'app-history-dialog',
  templateUrl: './history-dialog.component.html',
  styleUrls: ['./history-dialog.component.css']
})
export class HistoryDialogComponent  implements OnInit{
  dateBegin: string = environment.date_begin;
  dateEnd: string = environment.date_end;
  timeBegin: string = environment.time_begin;
  timeEnd: string = environment.time_end;
  lpuId: string = environment.kod_mo;
  prim: string = environment.prim;
  typeMo: string = environment.type_mo;
  address:string = environment.address;

  dataShow: disp_table4[] | null;
  days: string;
  constructor(@Inject(MAT_DIALOG_DATA) public data: any,public dialogRef: MatDialogRef<HistoryDialogComponent>){

  }

  ngOnInit(){
    this.dataShow = this.data.dataShow as disp_table4[];
    this.days = this.data.days;
  }


}
