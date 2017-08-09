import {Injectable} from '@angular/core';
import {NAMES} from '../dataset/names';
import {COLORS} from '../dataset/colors';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {environment} from '../../environments/environment';



export let LATEST_ID: number = 0;

export interface UserData {
  //enp: string;
  personSurname: string;
  personKindfirstname: string;
  personKindlastname: string;
  personBirthday: string;
  personLinksmoestablishmentid: string;
  edit: string;
  years: string;
  personEstablishmentambul: string;
	  personadd:{
		  tele2: string;
		  teledom: string;
		  telework: string;
	  }
  /*start_date_etap1:string;
  end_date_etap1:string;
  start_date_etap2:string;
  end_date_etap2:string;
  ref_id_person:string;
  pm_god:string;
  pm_kvartal:string;
  adress:string;
  tel:string;
  pm_result:string;
  pm_HOSPITAL_RESULT:string*/
}

@Injectable()
export class PeopleDatabase {
  
  dataChange: BehaviorSubject<UserData[]> = new BehaviorSubject<UserData[]>([]);

  get data(): UserData[] { return this.dataChange.value; }
  serverUrl : string = environment.BACKEND_URL + "/rest/prophylactic";
  
  constructor(private http: Http) {
      this.initialize();
  }
  
  private handleError(error: any): Promise<any> {
  console.error('An error occurred', error); // for demo purposes only
  return Promise.reject(error.message || error);
}
  
  searchPersonInsur(per_data: any): Promise<any> {
	let headers = new Headers({'Content-Type': 'application/json'});
	return this.http
	  .post(this.serverUrl + '/search_person_insur', JSON.stringify(per_data), {headers: headers})
	  .toPromise()
	  // lenght передаем как флаг отсутствия записи в рс ерз
	  .then(res =>{
	  
	  let tmp_data = res.json();
	   
	  tmp_data[0].personLinksmoestablishmentid === 1 ?  tmp_data[0].personLinksmoestablishmentid = environment.linksmo_1  :
	  tmp_data[0].personLinksmoestablishmentid === 2 ?  tmp_data[0].personLinksmoestablishmentid = environment.linksmo_2  :
	  tmp_data[0].personLinksmoestablishmentid === 4 ?  tmp_data[0].personLinksmoestablishmentid = environment.linksmo_4  : environment.otkreplen
	  
	  tmp_data.length != 0 ? this.addPerson_t(tmp_data[0]) : tmp_data.length
	  
	  })
	  .catch(this.handleError);
  }
  searchPersonInformir(per_data: any): Promise<any[]> {
	let headers = new Headers({'Content-Type': 'application/json'});
	return this.http
	  .post(this.serverUrl + '/search_informed', JSON.stringify(per_data), {headers: headers})
	  .toPromise()
	  .then(res => res.json())
	  
  }
    searchPlanPersonInformir(adressid: string): Promise<any[]> {
	let headers = new Headers({'Content-Type': 'application/json'});
	return this.http
	  .post(this.serverUrl + '/search_plan_informed/'+adressid,null,{headers: headers})
	  .toPromise()
	  .then(res => res.json())
  }

  
  searchPersonGer(per_data: any): Promise<any> {
	let headers = new Headers({'Content-Type': 'application/json'});
	
	return this.http
	  .post(this.serverUrl + '/search_ger', JSON.stringify(per_data), {headers: headers})
	  .toPromise()
	  .then(res => res.json()[0])
	  
  } 
  

  initialize() {
    LATEST_ID = 0;
    this.dataChange.next([]);
    //for (let i = 0; i < 0; i++) { this.addPerson(); }
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

  
   /*addPerson() {
    const name = 'test name'
    const copiedData = this.data.slice();
    
    copiedData.push({
      edit: '',
      //enp: 'test',
      personSurname: 'Sergh',
	  personKindfirstname: 'Sergh',
	  personKindlastname: 'Sergh',
	  personBirthday: '22.07.2007',
	  personLinksmoestablishmentid: '',
	  personadd:{
	  tele2: '4534563463',
	  teledom: '4543534534',
	  telework: '43543543543'
	  }
	  /*start_date_etap1:'string',
	  end_date_etap1:'string',
	  start_date_etap2:'string',
	  end_date_etap2:'string',
	  ref_id_person:'string',
	  pm_god:'string',
	  pm_kvartal:'string',
	  adress:'string',
	  tel:'string',
	  pm_result:'string',
	  pm_HOSPITAL_RESULT:'string'
    });

    this.dataChange.next(copiedData);
  }*/
  
  
  
  
}
