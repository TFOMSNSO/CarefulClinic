import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {User} from "../model/user";
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import {moInfo, moInfo4} from "./modatabase.service";
import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';


const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
const EXCEL_EXTENSION = '.xlsx';




export class disp_table4{
  dateInsert: string;
  lpuId: string;
  address: string;
  dateBegin: string;
  dateEnd: string;
  timeBegin: string;
  timeEnd: string;
  typeMo: string;
  prim: string;
}



@Injectable()
export class HistoryService {
  serverUrl: string = environment.BACKEND_URL + "/rest/mo_schedule";
  currentUser: User;


  constructor(private httpClient: HttpClient) { }





  exportAsExcelFile(json: any[], excelFileName: string,table:number):void{
    var datacust = json.slice();

    let headers = [];
    let headers4 = ["dateInsert","lpuId","address","dateBegin","dateEnd","timeBegin","timeEnd","typeMo","prim"];
    let headers1 = ["dateInsert","lpuId","otdId","address","phone","typeMo","prof","prim","monday","tuesday","wednesday","thursday","friday","saturday","sunday"];
    switch(table){
      case 4:{
        let firstRow:disp_table4 = new disp_table4();
        firstRow.dateBegin = 'Дата начала';
        firstRow.dateEnd = 'Дата окончания';
        firstRow.address = 'Адрес проведения';
        firstRow.lpuId = 'Код МО';
        firstRow.typeMo = 'Тип МО';
        firstRow.prim = 'Примечание';
        firstRow.timeBegin = 'Время начала';
        firstRow.timeEnd = 'Время окончания';
        firstRow.dateInsert = 'Дата ' + excelFileName;
        datacust.unshift(firstRow);
        headers = headers4;
        excelFileName = excelFileName.trim() + '_бригады_';
        break;
      }
      case 1:{
        headers = headers1;
        let firstRow = {
          dateInsert: 'Дата ' + excelFileName,
          lpuId: 'Код МО',
          otdId: 'Код отделения',
          address: 'Адрес',
          phone: 'Телефон',
          typeMo: 'Тип Мед.Осмотра',
          prof: '№ каб. профилактики',
          prim: 'Примечание',
          monday:'Понедельник',
          tuesday:'Вторник',
          wednesday:'Среда',
          thursday:'Четверг',
          friday:'Пятница',
          saturday:'Суббота',
          sunday:'Воскресенье',
        };

        for(let i in datacust){
          datacust[i].monday = datacust[i].dates[0].dy == '1' ?'C ' + datacust[i].dates[0].timeBegin + ' до ' + datacust[i].dates[0].timeEnd : '-';
          datacust[i].tuesday = datacust[i].dates[1].dy == '1' ?'C ' + datacust[i].dates[1].timeBegin + ' до ' + datacust[i].dates[1].timeEnd : '-';
          datacust[i].wednesday = datacust[i].dates[2].dy == '1' ?'C ' + datacust[i].dates[2].timeBegin + ' до ' + datacust[i].dates[2].timeEnd : '-';
          datacust[i].thursday = datacust[i].dates[3].dy == '1' ?'C ' + datacust[i].dates[3].timeBegin + ' до ' + datacust[i].dates[3].timeEnd : '-';
          datacust[i].friday = datacust[i].dates[4].dy == '1' ?'C ' + datacust[i].dates[4].timeBegin + ' до ' + datacust[i].dates[4].timeEnd : '-';
          datacust[i].saturday = datacust[i].dates[5].dy == '1' ?'C ' + datacust[i].dates[5].timeBegin + ' до ' + datacust[i].dates[5].timeEnd : '-';
          datacust[i].sunday = datacust[i].dates[6].dy == '1' ?'C ' + datacust[i].dates[6].timeBegin + ' до ' + datacust[i].dates[6].timeEnd : '-';
          delete datacust[i].id;
        }

        datacust.unshift(firstRow);
        excelFileName = excelFileName.trim() + '_работаМО_';
      }
    }
    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(datacust,{header:headers,skipHeader:true});
    const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
    const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
    this.saveAsExcelFile(excelBuffer, excelFileName);
  }
  private saveAsExcelFile(buffer: any, fileName: string): void {
    let now = new Date;
    const data: Blob = new Blob([buffer], {type: EXCEL_TYPE});
    FileSaver.saveAs(data, fileName  +now.getDate() + '_' + (now.getMonth()+1) + '_' + now.getFullYear() + EXCEL_EXTENSION);
  }

  getHistory(table:string,type:string,day:string){
    return this.httpClient.post(this.serverUrl + '/'+table+'_'+type+'_days',{days:day},{headers:{ 'Content-Type': 'application/json' }}).toPromise();
  }


}
