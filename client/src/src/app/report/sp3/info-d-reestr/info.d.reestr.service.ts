import { Injectable, OnDestroy } from '@angular/core';
import * as FileSaver from 'file-saver';
import { Http, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import {environment} from '../../../../environments/environment';
import { ListExcelFiles } from '../../../model/list.files.excel';




@Injectable()
export class InfoDReestrService{

  serverUrl : string = environment.BACKEND_URL + "/rest/sp3/d_reestr";
  

  constructor(private http: Http) {  console.log('Start!')}
  
  
	listFiles_1(data : number): Promise<ListExcelFiles[]> {
		let headers = new Headers({'Content-Type': 'application/json'});
		return this.http.get(`${this.serverUrl}/d_reestr/${data}`,{headers: headers})
               .toPromise()
               .then(response => response.json() as ListExcelFiles[]);
	}	
	
	/*
		Загрузка файла д учета
	*/  
	downloadFile_2(data: string,data2: number,place: string):Promise<any>{
	 const headers = new Headers({'Content-Type': 'application/json', 'Accept': '*'});
		const options = new RequestOptions({headers: headers});
		options.responseType = ResponseContentType.Blob;
		 return this.http.get(`${this.serverUrl}/info_d_reestr/${place}/${data2}/${data}`, options)
		  .toPromise()
			.then(response =>response);
	}
	
	
	downloadFile_d_reestr(date1: string,date2: string,place: number):Promise<any>{
	
			let obj2 = {
				date1: date1,
				date2: date2,
				place: place+''
			}
			
			let headers = new Headers({'Content-Type': 'application/json'});
			return this.http
			  .post(this.serverUrl + '/d_report',JSON.stringify(obj2), {headers: headers})
			  .debounceTime(500)
			  .toPromise()
			  .then(response =>response);
	}
	
	
	listFilesD_reestr(data : number): Promise<ListExcelFiles[]> {
	  let headers = new Headers({'Content-Type': 'application/json'});
		return this.http.get(`${this.serverUrl}/list_files/${data}`,{headers: headers})
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
		 return this.http.get(`${this.serverUrl}/download/${data2}/${data}`, options)
		  .toPromise()
			.then(response =>response);
	}
  
  
  
}
