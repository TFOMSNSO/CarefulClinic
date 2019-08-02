import { Injectable, OnDestroy } from '@angular/core';
import * as FileSaver from 'file-saver';
import { Http, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import {environment} from '../../../../environments/environment';
import { ListExcelFiles } from '../../../model/list.files.excel';




@Injectable()
export class ExpertiseService{

  serverUrl : string = environment.BACKEND_URL + "/rest/sp3/expertise";


  constructor(private http: Http) {  console.log('Start ExpertiseService!')}


	downloadFile_expertise(date1: string,date2: string,place: number):Promise<any>{
	 /*const headers = new Headers({'Content-Type': 'application/json', 'Accept': '*'});
		const options = new RequestOptions({headers: headers});
		options.responseType = ResponseContentType.Blob;
		 return this.http.get(`${this.serverUrl}/3a_report/${date1}/${date2}/${place}`, options)
		  .toPromise()
			.then(response =>response);

      console.log(date1);
      console.log(date2);*/
			let obj2 = {
				date1: date1,
				date2: date2,
				place: place+''
			}

			let headers = new Headers({'Content-Type': 'application/json'});
			return this.http
			  .post(this.serverUrl + '/3a_report_new',JSON.stringify(obj2), {headers: headers})
			  .debounceTime(500)
			  .toPromise()
			  .then(response =>response);
	}


	downloadFile_expertise3b(date1: string,date2: string,place: number):Promise<any>{

			let obj2 = {
				date1: date1,
				date2: date2,
				place: place+''
			}

			let headers = new Headers({'Content-Type': 'application/json'});
			return this.http
			  .post(this.serverUrl + '/3b_report_new',JSON.stringify(obj2), {headers: headers})
			  .debounceTime(500)
			  .toPromise()
			  .then(response =>response);
	}

	downloadFile_expertise3a3b(date1: string,date2: string,place: number):Promise<any>{

			let obj2 = {
				date1: date1,
				date2: date2,
				place: place+''
			}

			let headers = new Headers({'Content-Type': 'application/json'});
			return this.http
			  .post(this.serverUrl + '/3a3b_report_new',JSON.stringify(obj2), {headers: headers})
			  .debounceTime(500)
			  .toPromise()
			  .then(response =>response);
	}

  downloadFile_expertise3a3b_noNazrNoGosp(date1: string,date2: string,place: number):Promise<any>{

    let obj2 = {
      date1: date1,
      date2: date2,
      place: place+''
    }
    let headers = new Headers({'Content-Type': 'application/json'});
    return this.http
      .post(this.serverUrl + '/3a3b_noNazrNoGosp_report_new',JSON.stringify(obj2), {headers: headers})
      .debounceTime(500)
      .toPromise()
      .then(response =>response);
  }

  downloadFile_expertise_after_disp_3_group(date1: string,date2: string,place: number):Promise<any>{

    let obj2 = {
      date1: date1,
      date2: date2,
      place: place+''
    }
    let headers = new Headers({'Content-Type': 'application/json'});
    return this.http
      .post(this.serverUrl + '/after_disp_3_group_report',JSON.stringify(obj2), {headers: headers})
      .debounceTime(500)
      .toPromise()
      .then(response =>response);
  }


	listFilesExpertise(data : number): Promise<ListExcelFiles[]> {
	  let headers = new Headers({'Content-Type': 'application/json'});
		return this.http.get(`${this.serverUrl}/Sp3ReportExpertiseFiles/${data}`,{headers: headers})
				   .toPromise()
				   .then(response => response.json() as ListExcelFiles[]);
	}

	/*
		Метод предназначен для загрузки файла по полному пути.
	*/
	downloadFile(data: string,data2: number):Promise<any>{
	 const headers = new Headers({'Content-Type': 'application/json', 'Accept': '*'});
		const options = new RequestOptions({headers: headers});
		options.responseType = ResponseContentType.Blob;
		 return this.http.get(`${this.serverUrl}/downloadFile/${data2}/${data}`, options)
		  .toPromise()
			.then(response =>response);
	}




}
