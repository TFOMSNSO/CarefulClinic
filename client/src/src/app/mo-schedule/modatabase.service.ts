import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import {environment} from "../../environments/environment";
import {User} from "../model/user";
import {PeopleDatabase} from "../list-prophylactic/people-database";

//disp_Table1
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

  getHistoryT4():Promise<any>{
    let header = new HttpHeaders({'Content-Type': 'application/json'});
    return this.httpClient.get(this.serverUrl + "/table4_update",{headers: header}).toPromise();
  }

  getHistoryT4By(day):Promise<any>{
    return this.httpClient.post(this.serverUrl + '/table4_update_days',{days: day},{headers:{ 'Content-Type': 'application/json' }}).toPromise();
  }

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
  }


  exportExcel1(data: moInfo[]){
    if (data.length > 0) {
      let header = new HttpHeaders({'Content-Type': 'application/json'});
      this.httpClient.post(this.serverUrl + '/export_table_disp1', JSON.stringify(data), {headers: header})
        .toPromise().then((res) => {
        const filename = res['desccription'];
        this.peopleDatabase.downloadExcel(filename, this.currentUser['role'][0].id, 'download');
      })
    }
  }

  exportExcel2(data: moInfo2[]){
    if (data.length > 0) {
      let header = new HttpHeaders({'Content-Type': 'application/json'});
      this.httpClient.post(this.serverUrl + '/export_table_disp2', JSON.stringify(data), {headers: header})
        .toPromise().then((res) => {
        const filename = res['desccription'];
        this.peopleDatabase.downloadExcel(filename, this.currentUser['role'][0].id, 'download');
      })
    }
  }


  exportExcel3(data: moInfo3[]){
    if (data.length > 0) {
      let header = new HttpHeaders({'Content-Type': 'application/json'});
      this.httpClient.post(this.serverUrl + '/export_table_disp3', JSON.stringify(data), {headers: header})
        .toPromise().then((res) => {
        const filename = res['desccription'];
        this.peopleDatabase.downloadExcel(filename, this.currentUser['role'][0].id, 'download');
      })
    }
  }


  exportExcel4(data: moInfo4[]){
    if (data.length > 0) {
      let header = new HttpHeaders({'Content-Type': 'application/json'});
      this.httpClient.post(this.serverUrl + '/export_table_disp4', JSON.stringify(data), {headers: header})
        .toPromise().then((res) => {
        const filename = res['desccription'];
        this.peopleDatabase.downloadExcel(filename, this.currentUser['role'][0].id, 'download');
      })
    }
  }

  exportExcel5(data: moInfo4[]){
    if (data.length > 0) {
      let header = new HttpHeaders({'Content-Type': 'application/json'});
      this.httpClient.post(this.serverUrl + '/export_table_disp5', JSON.stringify(data), {headers: header})
        .toPromise().then((res) => {
        const filename = res['desccription'];
        this.peopleDatabase.downloadExcel(filename, this.currentUser['role'][0].id, 'download');
      })
    }
  }


  exportExcel6(data: moInfo6[]){
    if (data.length > 0) {
      let header = new HttpHeaders({'Content-Type': 'application/json'});
      this.httpClient.post(this.serverUrl + '/export_table_disp6', JSON.stringify(data), {headers: header})
        .toPromise().then((res) => {
        const filename = res['desccription'];
        this.peopleDatabase.downloadExcel(filename, this.currentUser['role'][0].id, 'download');
      })
    }
  }

}
