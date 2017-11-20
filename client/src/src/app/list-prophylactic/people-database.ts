import {Injectable} from '@angular/core';
import {NAMES} from '../dataset/names';
import {COLORS} from '../dataset/colors';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import { Http, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {environment} from '../../environments/environment';
import { User } from '../model/user';
import { ListExcelFiles } from '../model/list.files.excel';
import * as FileSaver from 'file-saver';




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
export class PeopleDatabase {
  
  dataChange: BehaviorSubject<UserData[]> = new BehaviorSubject<UserData[]>([]);

  get data(): UserData[] { return this.dataChange.value; }
  serverUrl : string = environment.BACKEND_URL + "/rest/prophylactic";
  currentUser: User;
  
  constructor(private http: Http) {
      this.initialize();
      
  }
  
  private handleError(error: any): Promise<any> {
 	console.log(error); // for demo purposes only
  	return Promise.reject(error.message || error);
  //return new Promise((resolve, reject) =>{}).then(res=> 0);
}
  
  downloadExcel(data: string,data2: number,place: string){
 const headers = new Headers({'Content-Type': 'application/json', 'Accept': '*'});
    const options = new RequestOptions({headers: headers});
    options.responseType = ResponseContentType.Blob;
    this.http.get(`${this.serverUrl}/download/${place}/${data2}/${data}`, options)
      .subscribe((response) => {
                var blob = new Blob([response.blob()], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});
                var filename = data;
                FileSaver.saveAs(blob, filename);
        })
}
  
  /* Загрузка сформированных файлов
  */
  listFiles(data : number): Promise<ListExcelFiles[]> {
  let headers = new Headers({'Content-Type': 'application/json'});
    return this.http.get(`${this.serverUrl}/listExcelFiles/${data}`,{headers: headers})
               .toPromise()
               .then(response => response.json() as ListExcelFiles[]);
  }
  
  /* Загрузка файлов на закачку
  */
  listFiles2(data : number): Promise<ListExcelFiles[]> {
  let headers = new Headers({'Content-Type': 'application/json'});
    return this.http.get(`${this.serverUrl}/listUpload/${data}`,{headers: headers})
               .toPromise()
               .then(response => response.json() as ListExcelFiles[]);
  }
  
  upload(fileList : Array<File>): Promise<any>{
  	 if(fileList.length > 0) {
  	 	this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  	 	let us = this.currentUser['role'][0].id+'';
        let file: File = fileList[0];
        let formData:FormData = new FormData();
        formData.append('uploadFile', file, encodeURIComponent(file.name));
        let headers = new Headers();
        /** No need to include Content-Type in Angular 4 */
        //headers.append('Content-Type', 'multipart/form-data');
        headers.append('Accept', 'application/json');
        headers.append('Authorization', us);
        let options = new RequestOptions({ headers: headers });
        return this.http.post(this.serverUrl + '/upload', formData, options)
        .toPromise()
        .then(res =>res);
           /* .map(res => res.json())
            .catch(error => Observable.throw(error))
            .subscribe(
                data => console.log('success'),
                error => console.log(error)
            )*/
    }
  }
  
  searchPersonKeys(data: any): Promise<any>{
  	let headers = new Headers({'Content-Type': 'application/json'});
  	return this.http
	  .post(this.serverUrl + '/search_person_keys', JSON.stringify(data), {headers: headers})
	  .toPromise()
	  .then(res =>{
		  let tmp_data =  res.json();
		  this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
			  for(let indexmas in tmp_data){
				  if(this.currentUser['role'][0].id !== 777 && this.currentUser['role'][0].id !== Number(tmp_data[indexmas].personLinksmoestablishmentid) ) continue;
				  tmp_data[indexmas].currentUser = this.currentUser['role'][0].id;
				  this.addPerson_t(tmp_data[indexmas]); 
			  }
			   tmp_data.length;
	  })
  	.catch(this.handleError);
  }
  
  searchPersonInsur(per_data: any): Promise<any> {
	let headers = new Headers({'Content-Type': 'application/json'});
	return this.http
	  .post(this.serverUrl + '/search_person_insur', JSON.stringify(per_data), {headers: headers})
	  .toPromise()
	  // lenght передаем как флаг отсутствия записи в рс ерз
	  .then(res =>{
	  
	  let tmp_data = res.json();
	  
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
  
  exportToExcel(per_data: any): Promise<any[]> {
  	let headers = new Headers({'Content-Type': 'application/json'});
  	return this.http
	  .post(this.serverUrl + '/exportToexcel', JSON.stringify(per_data), {headers: headers})
	  .toPromise()
	  .then(res => res.json())
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

  searchPersonSurveyr(per_data: any): Promise<any> {
  	let headers = new Headers({'Content-Type': 'application/json'});
  	
  	return this.http
	  .post(this.serverUrl + '/survey_inform', JSON.stringify(per_data), {headers: headers})
	  .toPromise()
	  .then(res => res.json())
	  
  }
  addResultSurvey(per_data: any): Promise<any> {
		let headers = new Headers({'Content-Type': 'application/json'});
	return this.http
	  .post(this.serverUrl + '/insert_pm_a', JSON.stringify(per_data), {headers: headers})
	  .toPromise()
	  .then(res => {
	  console.log(JSON.stringify(res));
	  res;
	  })
	  
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
}
