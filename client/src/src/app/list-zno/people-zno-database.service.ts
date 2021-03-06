import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {User} from "../model/user";
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import { Http, Headers } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';


export let LATEST_ID: number = 0;

export interface UserData {
  enp: string;
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

export interface zno_user{
  id1: string;
  id2: string;
  enp: string;
  fam: string;
  im: string;
  ot: string;
  dr: string;
  date_insert: string;
  state_insur: string;
  date_insur: string;
  state_registr: string;
  date_state: string;
  state_insert: string;
  smo: string;
}


@Injectable()
export class PeopleZnoDatabaseService {
  dataChange: BehaviorSubject<UserData[]> = new BehaviorSubject<UserData[]>([]);

  get data(): UserData[] { return this.dataChange.value; }
  serverUrl : string = environment.BACKEND_URL + "/rest/zno";
  currentUser: User;

  constructor(private http: Http,private httpClient: HttpClient) {
    this.initialize();
  }



  searchTreatment(id:any): Promise<any>{
    let headers = new Headers({'Content-Type': 'application/json'});
    return this.http.post(this.serverUrl + '/treatment',id,{headers: headers})
      .toPromise().then(res => res.json());
  }

  findExpertise(person:any):any{
    let headers = new Headers({'Content-Type': 'application/json'});
    return this.http.post(this.serverUrl + '/expertise',JSON.stringify(person),{headers: headers})
      .toPromise().then(res => res.json());
  }


  private handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }

  searchPersonZNO(per_data: any): Promise<any>{
    let headers = new Headers({'Content-Type': 'application/json'});

    return this.http
      .post(this.serverUrl + '/search_person_zno', JSON.stringify(per_data),{headers: headers})
      .toPromise()
      // lenght передаем как флаг отсутствия записи в рс ерз
      .then(res =>{
        let tmp_data = res.json();
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if(tmp_data.length === 0) return tmp_data.length;

        tmp_data[0].currentUser = this.currentUser['role'][0].id;
        tmp_data.length != 0  ? this.addPerson_t(tmp_data[0]) : tmp_data.length

      })
      .catch(function(){return -1;
      });
  }

  searchPersonKeysZno(data: any): Promise<any>{
    // console.log(JSON.stringify(data));
    let headers = new Headers({'Content-Type': 'application/json'});
    return this.http
      .post(this.serverUrl + '/search_person_zno_keys', JSON.stringify(data), {headers: headers})
      .toPromise()
      .then(res =>{
        let tmp_data =  res.json();
        console.log("length:" + tmp_data.length);

        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        for(let indexmas in tmp_data){
          tmp_data[indexmas].currentUser = this.currentUser['role'][0].id;
          console.log(JSON.stringify(tmp_data[indexmas]) + '\n\n');
          this.addPerson_t(tmp_data[indexmas]);
        }
      })
      .catch(this.handleError);
  }




  addPerson_t(data: any) {
    let date1 = new Date().getTime();
    let date2 = new Date((data.personBirthday).replace( /(\d{2}).(\d{2}).(\d{4})/, "$3-$2-$1") ).getTime();
    let  years_count= ((date1 - date2)/31536000000);
    data.years = years_count.toString().substr(0,2);
    const copiedData = this.data.slice();

    copiedData.push(data);

    this.dataChange.next(copiedData);
  }

  exportToExcel(per_data: any): Promise<any> {
    //console.log('exportToExcel:' + JSON.stringify(per_data));
    let headers = new Headers({'Content-Type': 'application/json'});
    return this.http
      .post(this.serverUrl + "/export_excel_zno", JSON.stringify(per_data), {headers: headers})
      .toPromise()
      .then(res => res != null ? res.json() : res)
  }


  initialize() {
    LATEST_ID = 0;
    this.dataChange.next([]);
    //for (let i = 0; i < 0; i++) { this.addPerson(); }
  }

}
