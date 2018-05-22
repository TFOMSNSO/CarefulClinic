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
  
  
  
}
