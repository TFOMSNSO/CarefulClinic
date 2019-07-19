import { Component, Inject, OnInit} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatTabChangeEvent} from '@angular/material';
import {environment} from "../../../../environments/environment";
import {ModatabaseService, moInfo4} from "../../modatabase.service";
import {disp_table4, HistoryService} from "../../history.service";
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {User} from "../../../model/user";




@Component({
  selector: 'app-history-dialog',
  templateUrl: './history-dialog.component.html',
  styleUrls: ['./history-dialog.component.css'],
  providers: [HistoryService]
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


  currentUser: User;
  dataShow: disp_table4[] | null;
  dataShowInsert: disp_table4[] | null;
  dataShowDelete: disp_table4[] | null;
  days: string;
  currentTab: number;
  currentTabName: string;
  show: boolean = false;
  constructor(@Inject(MAT_DIALOG_DATA) public data: any,public dialogRef: MatDialogRef<HistoryDialogComponent>,public historyService: HistoryService){

  }

  /*TODO использовать один и тот же массив для всех вкладок*/

  ngOnInit(){
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.dataShow = this.dataShowDelete = this.dataShowInsert = [];
    this.days = this.data.days;
    this.currentTab = 0;
    this.currentTabName = 'изменения';
    this.refreshTab(0);
  }











  check(event: any){
    this.currentTab = event.index;
    this.currentTabName = event.tab.textLabel.split(' ')[1];
    this.refreshTab(this.currentTab);
    //console.log('currentTab:' + this.currentTabName);
  }

  refresh(){
    this.refreshTab(this.currentTab);
  }



  export(){
    //this.moDatabase.exportHistoryExcel4(this.currentTabName,this.dataShow);
    switch (this.currentTab){
      case 0:this.historyService.exportAsExcelFile(this.dataShow,this.currentTabName,4); break;
      case 1:this.historyService.exportAsExcelFile(this.dataShowInsert,this.currentTabName,4); break;
      case 2:this.historyService.exportAsExcelFile(this.dataShowDelete,this.currentTabName,4); break;
    }
  }

  refreshTab(tab: number){
    this.show = true;
    switch (tab){
      case 0:{
        this.dataShow = [];

        this.historyService.getHistory('table4','update',this.days).then(res =>{
          this.dataShow = res as disp_table4[];
          //console.log(JSON.stringify(res));
          for(let a in this.dataShow){
              delete this.dataShow[a]['id'];
          }
          //console.log(JSON.stringify(this.dataShow));
          this.show = false;
        });
        break;
      }
      case 1:{
        this.dataShowInsert = [];
        this.historyService.getHistory('table4','insert',this.days).then(res => {
          this.dataShowInsert = res as disp_table4[];
          for(let a in this.dataShowInsert){
            delete this.dataShowInsert[a]['id'];
          }
          this.show = false;
        });
        break;
      }
      case 2:{
        this.dataShowDelete = [];
        this.historyService.getHistory('table4','delete',this.days).then(res => {
          this.dataShowDelete = res as disp_table4[];
          for(let a in this.dataShowDelete){
            delete this.dataShowDelete[a]['id'];
          }
          this.show = false;
        });
        break;
      }
    }
  }

}
