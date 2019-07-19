import { Component, Inject, OnInit} from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatTabChangeEvent} from '@angular/material';
import {environment} from "../../../../environments/environment";
import {disp_table4, HistoryService} from "../../history.service";
import {moInfo} from "../../modatabase.service";

@Component({
  selector: 'app-historydialog1',
  templateUrl: './historydialog1.component.html',
  styleUrls: ['./historydialog1.component.css'],
  providers:[HistoryService]
})
export class Historydialog1Component implements OnInit {
  lpuId: string = environment.kod_mo;
  kodOtd: string = environment.kod_otd;
  phone: string = environment.phone_number;
  typeMo: string = environment.type_mo;
  otkProf: string = environment.prof;
  address: string = environment.address;
  prim: string = environment.prim;
  about: string = environment.about;

  days: string;
  currentTab: number;
  currentTabName: string;

  dataShow:moInfo[] | null;
  dataShowInsert:moInfo[] | null;
  dataShowDelete:moInfo[] | null;


  constructor(@Inject(MAT_DIALOG_DATA) public data: any,public historyService: HistoryService) { }

  ngOnInit() {
    this.days = this.data.days;
    this.dataShow = this.dataShowDelete = this.dataShowInsert = [];
    this.currentTab = 0;
    this.currentTabName = 'изменения';
    this.refreshTab(0);
  }

  refresh(){
    this.refreshTab(this.currentTab);
  }

  check(event: any){
    this.currentTab = event.index;
    this.currentTabName = event.tab.textLabel.split(' ')[1];
    this.refreshTab(this.currentTab);
    //console.log('currentTab:' + this.currentTabName);
  }

  export(){
    //this.moDatabase.exportHistoryExcel4(this.currentTabName,this.dataShow);
    switch (this.currentTab){
      case 0:this.historyService.exportAsExcelFile(this.dataShow,this.currentTabName,1); break;
      case 1:this.historyService.exportAsExcelFile(this.dataShowInsert,this.currentTabName,1); break;
      case 2:this.historyService.exportAsExcelFile(this.dataShowDelete,this.currentTabName,1); break;
    }
  }


  refreshTab(tab: number){
    switch (tab){
      case 0:{
        this.dataShow = [];
        this.historyService.getHistory('table1','update',this.days).then(res =>{
          //console.log('res:' + JSON.stringify(res));
          for (let i in res) {
            res[i].dates.sort(function (a,b) {
              return Number(a.dw) - Number(b.dw);
            })
          }
          this.dataShow = res as moInfo[];

          //console.log(JSON.stringify(this.dataShow));
        });
        break;
      }
      case 1:{
        this.dataShowInsert = [];
        this.historyService.getHistory('table1','insert',this.days).then(res => {
          for (let i in res) {
            res[i].dates.sort(function (a,b) {
              return Number(a.dw) - Number(b.dw);
            })
          }
          this.dataShowInsert = res as moInfo[];
          //console.log(JSON.stringify(res));
        });
        break;
      }
      case 2:{
        this.dataShowDelete = [];
        this.historyService.getHistory('table1','delete',this.days).then(res => {
          for (let i in res) {
            res[i].dates.sort(function (a,b) {
              return Number(a.dw) - Number(b.dw);
            })
          }
          this.dataShowDelete = res as moInfo[];
          //console.log(JSON.stringify(res));
        });
        break;
      }
    }
  }
}
