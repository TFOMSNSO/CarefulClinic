import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {UserData} from "../list-zno/people-zno-database.service";
import {environment} from "../../environments/environment";



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


@Injectable()
export class ModatabaseService {
  week:string[] = ['Понедельник','Вторник','Среда','Четверг','Пятница','Суббота','Воскресенье'];
  serverUrl : string = environment.BACKEND_URL + "/rest/mo_schedule";
  dataChange: BehaviorSubject<moInfo[]> = new BehaviorSubject<moInfo[]>([]);
  tempdata:any;

  get data(): moInfo[] { return this.dataChange.value; }


  constructor(private httpClient : HttpClient) {
    this.init();
  }

  init(){
    console.log('init');
    this.getAll();
  }

  //таблица 1. получаем список всех мо и график их работы
  getAll(){
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


}
