import { Injectable, OnDestroy } from '@angular/core';
import * as FileSaver from 'file-saver';
import { Http, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import {environment} from '../../../../environments/environment';
import { ListExcelFiles } from '../../../model/list.files.excel';




@Injectable()
export class ReportStatAssentService{

  serverUrl : string = environment.BACKEND_URL + "/rest/assent";
  

  constructor(private http: Http) {  console.log('Start!')}
  
  
	/* Формирование отчета 'Иноформация о количестве информационных согласий'	*/
	downloadFile_assent(date1: string,date2: string,place: number):Promise<any>{
	 
			let obj2 = {
				date1: date1,
				date2: date2,
				place: place+''
			}
			
			let headers = new Headers({'Content-Type': 'application/json'});
			return this.http
			  .post(this.serverUrl + '/stat_assent',JSON.stringify(obj2), {headers: headers})
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
			  .post(this.serverUrl + '/3b_report',JSON.stringify(obj2), {headers: headers})
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
			  .post(this.serverUrl + '/3a3b_report',JSON.stringify(obj2), {headers: headers})
			  .debounceTime(500)
			  .toPromise()
			  .then(response =>response);
	}
	
	
	/* Список файлов (отчетов) из директории assent */
	
	listFilesAssent(data : number): Promise<ListExcelFiles[]> {
	  let headers = new Headers({'Content-Type': 'application/json'});
		return this.http.get(`${this.serverUrl}/list_reports_assent/${data}`,{headers: headers})
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
