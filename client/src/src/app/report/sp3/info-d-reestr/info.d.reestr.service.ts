import { Injectable, OnDestroy } from '@angular/core';
import * as FileSaver from 'file-saver';
import { Http, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import {environment} from '../../../../environments/environment';
import { ListExcelFiles } from '../../../model/list.files.excel';
import { NgxLoremIpsumService } from 'ngx-lorem-ipsum';



export class JobInfo{
  id: number;
  state: string;
  dateInsert: string;
  dateInsertTrunc: string;
}



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
		�������� ����� � �����
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
				date1: date1 = "01.01.2018",//TODO временное решение. back end ожидает дату, но алгоритм изменился 04.12.2018 и дата больше не нужна. пока что... вдруг потом понадобится
				date2: date2 = "31.12.2018",//TODO временное решение. back end ожидает дату, но алгоритм изменился 04.12.2018 и дата больше не нужна. пока что... вдруг потом понадобится
				place: place+''
			}

			let headers = new Headers({'Content-Type': 'application/json'});
			return this.http
			  .post(this.serverUrl + '/d_report',JSON.stringify(obj2), {headers: headers})
			  .debounceTime(500)
			  .toPromise()
			  .then(response =>response);
	}

  makeDReestr():Promise<any> {
	  return this.http.get(this.serverUrl + '/make_dreestr').toPromise();
  }

  refreshDReestr():Promise<any>{
	  return this.http.get(this.serverUrl + '/refresh_dreestr').toPromise();
  }

  formDReestr():Promise<any>{
	  return this.http.get(this.serverUrl + '/form_dreestr').toPromise();
  }

	listFilesD_reestr(data : number): Promise<ListExcelFiles[]> {
	  let headers = new Headers({'Content-Type': 'application/json'});
		return this.http.get(`${this.serverUrl}/list_files/${data}`,{headers: headers})
				   .toPromise()
				   .then(response => response.json() as ListExcelFiles[]);
	}


	downloadFile(data: string,data2: number):Promise<any>{
	 const headers = new Headers({'Content-Type': 'application/json', 'Accept': '*'});
		const options = new RequestOptions({headers: headers});
		options.responseType = ResponseContentType.Blob;
		 return this.http.get(`${this.serverUrl}/download/${data2}/${data}`, options)
		  .toPromise()
			.then(response =>response);
	}



}
