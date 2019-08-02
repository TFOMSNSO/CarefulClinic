import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import {environment} from "../../environments/environment";
import {User} from "../model/user";
import {PeopleDatabase} from "../list-prophylactic/people-database";
import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';


const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
const EXCEL_EXTENSION = '.xlsx';


const headers1 =  ["lpuId","otdId","address","phone","typeMo","prof","prim","monday","tuesday","wednesday","thursday","friday","saturday","sunday","dateInsert"];
const headers2 = ["lpuId","fio","dol","phone","prim"];
const headers3 = ["lpuId","kodOtd","usl","kab","typeMo","prim","monday","tuesday","wednesday","thursday","friday","saturday","sunday"];
const headers4 = ["lpuId","address","dateBegin","dateEnd","timeBegin","timeEnd","typeMo","prim"];
const headers6 = ["lpuId","kodOtd","kab","date","timeBegin","timeEnd","typeMo","prim"];

/*TODO: убрать функции экспорта, заменить их на exportAsExcelFile*/

//disp_Table1 webofoms@dume
export class moInfo{
  lpuId: string;
  otdId: string;
  address: string;
  phone: string;
  typeMo: string;
  Id: string;
  prof:string;
  dateInsert: string;
  prim: string;
  currentUser: any;
  dates: jobtime[];
}
//disp_table2
export class moInfo2{
  lpuId: string;
  fio: string;
  dol: string;
  phone: string;
  prim: string;
  currentUser: any;
}

export class moInfo3 {
  lpuId: string;
  kodOtd: string;
  usl: string;
  kab: string;
  typeMo: string;
  prim: string;
  currentUser: any;
  dates: jobtime[];
}


//disp_table_dt
export class jobtime{
  Id: string;
  dw:string;
  dy:string;
  timeBegin:string;
  timeEnd:string;
}

//disp_table4
export class moInfo4{
  lpuId: string;
  address: string;
  dateBegin: string;
  dateEnd: string;
  timeBegin: string;
  timeEnd: string;
  typeMo: string;
  prim: string;
  Id: string;
  dateInsert: string;
  currentUser: any;
}



export class moInfo6{
  lpuId: string;
  kodOtd: string;
  kab: string;
  date: string;
  timeBegin: string;
  Id: string;
  timeEnd: string;
  typeMo: string;
  prim: string;
  currentUser: any;
}

@Injectable()
export class ModatabaseService {
  week: string[] = ['Понедельник', 'Вторник', 'Среда', 'Четверг', 'Пятница', 'Суббота', 'Воскресенье'];
  serverUrl: string = environment.BACKEND_URL + "/rest/mo_schedule";
  currentUser: User;

  //disp_table1
  dataChange: BehaviorSubject<moInfo[]> = new BehaviorSubject<moInfo[]>([]);

  //disp_table2
  dataChange2: BehaviorSubject<moInfo2[]> = new BehaviorSubject<moInfo2[]>([]);

  //disp_table3
  dataChange3: BehaviorSubject<moInfo3[]> = new BehaviorSubject<moInfo3[]>([]);

  //disp_table4
  dataChange4: BehaviorSubject<moInfo4[]> = new BehaviorSubject<moInfo4[]>([]);

  //disp_table5
  dataChange5: BehaviorSubject<moInfo4[]> = new BehaviorSubject<moInfo4[]>([]);

  //disp_table6
  dataChange6: BehaviorSubject<moInfo6[]> = new BehaviorSubject<moInfo6[]>([]);

  get data(): moInfo[] {
    return this.dataChange.value;
  }

  get data2(): moInfo2[] {
    return this.dataChange2.value;
  }

  get data3(): moInfo3[]{
    return this.dataChange3.value;
  }

  get data4(): moInfo4[] {
    return this.dataChange4.value;
  }

  get data5(): moInfo4[]{
    return this.dataChange5.value;
  }

  get data6(): moInfo6[]{
    return this.dataChange6.value;
  }

  constructor(private httpClient: HttpClient,private peopleDatabase : PeopleDatabase) {
    this.init();
  }

  init() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    //console.log('user:' + this.currentUser['role'][0].id);
  }


  // экспорт таблиц в эксель
  exportAsExcelFile(json: any[],table : number):void{
    let excelFileName = '_';
    let datacust = json.slice();
    let headers = [];
    switch (table){
      case 1:{
        headers = headers1;
        let firstRow = {
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
        }
        datacust.unshift(firstRow);
        excelFileName = 'ГрафикРаботыМо_';
        break;
      }
      case 2:{
        headers = headers2;
        let firstRow = {
          lpuId: 'Код МО',
          fio: 'Контактное лицо(ФИО)',
          dol: 'Адрес',
          phone: 'Телефон',
          prim: 'Примечание',
          };
        datacust.unshift(firstRow);
        excelFileName = 'КонтактныеЛица_';
        break;
      }
      case 3:{
        headers = headers3;
        let firstRow = {
          lpuId: 'Код МО',
          kodOtd: 'Код отделения',
          usl: 'Услуга',
          kab: 'Кабинет',
          typeMo: 'Тип Мед.Осмотра',
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
        }
        datacust.unshift(firstRow);
        excelFileName = 'ПорядокМаршрутизации_';
        break;
      }
      case 4:{
        headers = headers4;
        let firstRow = {
          dateBegin : 'Дата начала',
          dateEnd : 'Дата окончания',
          address : 'Адрес проведения',
          lpuId : 'Код МО',
          typeMo : 'Тип Мед.Осмотра',
          prim : 'Примечание',
          timeBegin : 'Время начала',
          timeEnd : 'Время окончания'
        };
        datacust.unshift(firstRow);
        excelFileName = 'МобильныеБригады_';
        headers = headers4;
        break;
      }
      case 5:{
        headers = headers4;
        let firstRow = {
          dateBegin : 'Дата начала',
          dateEnd : 'Дата окончания',
          address : 'Населенный пункт',
          lpuId : 'Код МО',
          typeMo : 'Тип Мед.Осмотра',
          prim : 'Примечание',
          timeBegin : 'Время сбора',
          timeEnd : 'Время обратного прибытия'
        };
        datacust.unshift(firstRow);
        excelFileName = 'СрокиДоставки_';
        break;
      }
      case 6:{
        headers = headers6;
        let firstRow = {
          date : 'Дата проведения',
          kab : 'Кабинет',
          kodOtd : 'Код отделения',
          lpuId : 'Код МО',
          typeMo : 'Тип Мед.Осмотра',
          prim : 'Примечание',
          timeBegin : 'Время начала',
          timeEnd : 'Время окончания'
        };
        datacust.unshift(firstRow);
        excelFileName = 'СпециальныеДни_';
        break;
      }
    }
    for(let i in datacust){
      delete datacust[i].id;
      delete datacust[i].currentUser;
      delete datacust[i].dateInsert;
    }

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(datacust,{header:headers,skipHeader:true});
    const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
    const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
    this.saveAsExcelFile(excelBuffer, excelFileName);
  }
  private saveAsExcelFile(buffer: any, fileName: string): void {
    var now = new Date;
    const data: Blob = new Blob([buffer], {type: EXCEL_TYPE});
    FileSaver.saveAs(data, fileName  + now.getDate() + '_' + (now.getMonth()+1) + '_' + now.getFullYear() + EXCEL_EXTENSION);
  }




  getAll(table:string){
    this.httpClient.get(this.serverUrl + "/"+table+"_all").toPromise().then(res => {
      let temp = res;
      if(table == 'table1' || table == 'table3'){
        for (let i in temp) {
          temp[i].dates.sort(function (a,b) {
            return Number(a.dw) - Number(b.dw);
          })
          for (let a in temp[i].dates) {
            temp[i].dates[a].dw = this.week[Number(temp[i].dates[a].dw) - 1];
          }
        }
      }

      switch (table){
        case "table1":{
          this.dataChange.next(temp as moInfo[]);
          break;
        }
        case "table2":{
          this.dataChange2.next(temp as moInfo2[]);
          break;
        }
        case "table3":{
          this.dataChange3.next(temp as moInfo3[]);
          break;
        }
        case "table4":{
          this.dataChange4.next(temp as moInfo4[]);
          break;
        }
        case "table5":{
          this.dataChange5.next(temp as moInfo4[]);
          break;
        }
        case "table6":{
          this.dataChange6.next(temp as moInfo6[]);
          break;
        }
      }

    });
  }



  /*
  //таблица 1 - DISP_TABLE1(webofoms@dume). получаем список всех мо и график их работы
  getAllt1() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.httpClient.get(this.serverUrl + "/table1_all").toPromise().then(res => {
      let temp = res as moInfo[];
      for (let i in temp) {
        temp[i].currentUser = this.currentUser['role'][0].id;
        temp[i].dates.sort(function (a,b) {
            return Number(a.dw) - Number(b.dw);
        })
        for (let a in temp[i].dates) {
          temp[i].dates[a].dw = this.week[Number(temp[i].dates[a].dw) - 1];
        }
      }
      this.dataChange.next(temp);
    });
  }

  //DISP_TABLE2
  getAllt2(){
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.httpClient.get(this.serverUrl + "/table2_all").toPromise().then(res => {
      let temp = res as moInfo2[];
      for (let i in temp) {
        temp[i].currentUser = this.currentUser['role'][0].id;
      }
      this.dataChange2.next(temp);
    });
  }

  getAllt3(){
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.httpClient.get(this.serverUrl + "/table3_all").toPromise().then(res => {
      let temp = res as moInfo3[];
      for (let i in temp) {
        temp[i].currentUser = this.currentUser['role'][0].id;
        temp[i].dates.sort(function (a,b) {
          return Number(a.dw) - Number(b.dw);
        })


        for (let a in temp[i].dates) {
          temp[i].dates[a].dw = this.week[Number(temp[i].dates[a].dw) - 1];
        }
      }
      this.dataChange3.next(temp);
      //console.log(JSON.stringify(temp[0]));
    });
  }


  getAllt4() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.httpClient.get(this.serverUrl + "/table4_all").toPromise().then(res => {
      let temp = res as moInfo4[];

      for(let i in temp){
        temp[i].currentUser = this.currentUser['role'][0].id;
      }
      //console.log(JSON.stringify(temp[0]));
      this.dataChange4.next(temp);
    });
  }

  getAllt5(){
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.httpClient.get(this.serverUrl + "/table5_all").toPromise().then(res => {
      let temp = res as moInfo4[];

      for(let i in temp){
        temp[i].currentUser = this.currentUser['role'][0].id;
      }
      this.dataChange5.next(temp);
    });
  }

  getAllt6(){
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.httpClient.get(this.serverUrl + "/table6_all").toPromise().then(res => {
      let temp = res as moInfo6[];

      for(let i in temp){
        temp[i].currentUser = this.currentUser['role'][0].id;
      }
      this.dataChange6.next(temp);
    });
  }*/
}
