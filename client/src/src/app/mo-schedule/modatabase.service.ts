import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {environment} from "../../environments/environment";


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
  dates: jobtime[];
}

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
}

@Injectable()
export class ModatabaseService {
  week:string[] = ['Понедельник','Вторник','Среда','Четверг','Пятница','Суббота','Воскресенье'];
  serverUrl : string = environment.BACKEND_URL + "/rest/mo_schedule";

  //disp_table1
  dataChange: BehaviorSubject<moInfo[]> = new BehaviorSubject<moInfo[]>([]);

  //disp_table4
  dataChange4: BehaviorSubject<moInfo4[]> = new BehaviorSubject<moInfo4[]>([]);

  get data(): moInfo[] { return this.dataChange.value; }
  get data4(): moInfo4[] { return this.dataChange4.value; }

  constructor(private httpClient : HttpClient) {
    this.init();
  }

  init(){
    this.getAllt1();
  }

  //таблица 1 - DISP_TABLE1(webofoms@dume). получаем список всех мо и график их работы
  getAllt1(){
    this.httpClient.get(this.serverUrl + "/table1_all").toPromise().then(res =>{
      let temp = res as moInfo[];
      for(let i in temp){
        for(let a in temp[i].dates){
          temp[i].dates[a].dw = this.week[Number(temp[i].dates[a].dw)-1];
        }
      }
      this.dataChange.next(temp);
    });
  }


  getAllt4(){
    this.httpClient.get(this.serverUrl + "/table4_all").toPromise().then(res => {
      let temp = res as moInfo4[];
      this.dataChange4.next(temp);
    });
  }


}
