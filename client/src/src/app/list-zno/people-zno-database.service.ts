import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {User} from "../model/user";
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import { Http, Headers, RequestOptions, ResponseContentType } from '@angular/http';


export let LATEST_ID: number = 0;

export interface UserData {
  personSurname: string;
  personKindfirstname: string;
  personKindlastname: string;
  personBirthday: string;
  personLinksmoestablishmentid: string;
  edit: string;
  years: string;
  personEstablishmentambul: string;
  currentUser: number;
  personadd:{
    tele2: string;
    teledom: string;
    telework: string;
  };

  respGerl:[{
    start_date_etap1:string;
    end_date_etap1:string;
    start_date_etap2:string;
    end_date_etap2:string;
    ref_id_person:string;
    pm_god:string;
    pm_kvartal:string;
    adress:string;
    tel:string;
    pm_result:string;
    pm_HOSPITAL_RESULT:string;
  }]
}

@Injectable()
export class PeopleZnoDatabaseService {
  dataChange: BehaviorSubject<UserData[]> = new BehaviorSubject<UserData[]>([]);

  get data(): UserData[] { return this.dataChange.value; }
  serverUrl : string = environment.BACKEND_URL + "/rest/zno";
  currentUser: User;

  constructor(private http: Http) {
    this.initialize();
  }


  searchPersonZNO(per_data: any): Promise<any>{
    console.log('per_data: ' + JSON.stringify(per_data));
    console.log('people zno database:' + localStorage.getItem('currentUser'));
    let headers = new Headers({'Content-Type': 'application/json'});
    return this.http
      .post(this.serverUrl + '/search_person_zno', JSON.stringify(per_data), {headers: headers})
      .toPromise()
      // lenght передаем как флаг отсутствия записи в рс ерз
      .then(res =>{
        //res - response with status: 200 OK for URL:...  <- example
        let tmp_data = res.json();
        console.log('tmp_data.length:' + tmp_data.length);
        console.log('peopleZnoDatabase   tmp_data[0]:' + JSON.stringify(tmp_data[0]));
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));

        if(this.currentUser['role'][0].id !== 777 && this.currentUser['role'][0].id !== tmp_data[0].personLinksmoestablishmentid ) return 0;
        if(tmp_data.length === 0) return tmp_data.length;

        tmp_data[0].currentUser = this.currentUser['role'][0].id;

        // ставляю пустою структуру гэра если ее нет
        if(!("respGerl" in tmp_data[0])){
          tmp_data[0].respGerl=[{
            start_date_etap1:'',
            end_date_etap1:'',
            start_date_etap2:'',
            end_date_etap2:'',
            ref_id_person:'',
            pm_god:'',
            pm_kvartal:'',
            adress:'',
            tel:'',
            pm_result:'',
            pm_HOSPITAL_RESULT:''
          }];
        }

        tmp_data.length != 0  ? this.addPerson_t(tmp_data[0]) : tmp_data.length

      })
      .catch(function(){return -1;
      });
  }

  addPerson_t(data: any) {
    let date1 = new Date().getTime();
    let date2 = new Date((data.personBirthday).replace( /(\d{2}).(\d{2}).(\d{4})/, "$3-$2-$1") ).getTime();
    let  years_count= ((date1 - date2)/31536000000);
    data.years = years_count.toString().substr(0,2);
    const copiedData = this.data.slice();
    console.log(JSON.stringify(data));
    copiedData.push(data);

    this.dataChange.next(copiedData);
  }

  exportToExcel(per_data: any): Promise<any[]> {
    let headers = new Headers({'Content-Type': 'application/json'});
    return this.http
      .post(this.serverUrl + '/exportToexcel', JSON.stringify(per_data), {headers: headers})
      .toPromise()
      .then(res => res.json())
  }


  initialize() {
    LATEST_ID = 0;
    this.dataChange.next([]);
    //for (let i = 0; i < 0; i++) { this.addPerson(); }
  }

}
