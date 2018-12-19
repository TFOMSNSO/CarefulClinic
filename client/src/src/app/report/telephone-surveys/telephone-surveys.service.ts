import { Injectable, OnDestroy } from '@angular/core';
import { Http, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import {environment} from '../../../environments/environment';
import { ListExcelFiles } from '../../model/list.files.excel';





@Injectable()
export class TelephoneSurveysService{

  serverUrl : string = environment.BACKEND_URL + "/rest/telephonesurveys";


  constructor(private http: Http) {  console.log('Start!')}


  downloadFile(data2: number,place: string): Promise<any>{
    const headers = new Headers({'Content-Type': 'application/json', 'Accept': '*'});
    const options = new RequestOptions({headers: headers});
    options.responseType = ResponseContentType.Blob;
    return this.http.get(`${this.serverUrl}/download/${place}/${data2}`, options)
    /*.subscribe((response) => {
                  var blob = new Blob([response.blob()], {type: 'application/x-dbase'});
                  var filename = 'report.dbf';
                  FileSaver.saveAs(blob, filename);
          })*/
      .toPromise()
      .then(response =>response);
  }


  listFilesTable3(data : number): Promise<ListExcelFiles[]> {
    let headers = new Headers({'Content-Type': 'application/json'});
    return this.http.get(`${this.serverUrl}/listFilesTable3/${data}`,{headers: headers})
      .toPromise()
      .then(response => response.json() as ListExcelFiles[]);
  }

  listFilesTable4(data : number): Promise<ListExcelFiles[]> {
    let headers = new Headers({'Content-Type': 'application/json'});
    return this.http.get(`${this.serverUrl}/listFilesTable4/${data}`,{headers: headers})
      .toPromise()
      .then(response => response.json() as ListExcelFiles[]);
  }

  listFilesTable5(data : number): Promise<ListExcelFiles[]> {
    let headers = new Headers({'Content-Type': 'application/json'});
    return this.http.get(`${this.serverUrl}/listFilesTable5/${data}`,{headers: headers})
      .toPromise()
      .then(response => response.json() as ListExcelFiles[]);
  }

  listFilesTable6(data : number): Promise<ListExcelFiles[]> {
    let headers = new Headers({'Content-Type': 'application/json'});
    return this.http.get(`${this.serverUrl}/listFilesTable6/${data}`,{headers: headers})
      .toPromise()
      .then(response => response.json() as ListExcelFiles[]);
  }


  downloadFile_table3(data: string,data2: number,place: string):Promise<any>{
    const headers = new Headers({'Content-Type': 'application/json', 'Accept': '*'});
    const options = new RequestOptions({headers: headers});
    options.responseType = ResponseContentType.Blob;
    return this.http.get(`${this.serverUrl}/listFilesTable3Url/${place}/${data2}/${data}`, options)
      .toPromise()
      .then(response =>response);
  }

  downloadFile_table4(data: string,data2: number,place: string):Promise<any>{
    const headers = new Headers({'Content-Type': 'application/json', 'Accept': '*'});
    const options = new RequestOptions({headers: headers});
    options.responseType = ResponseContentType.Blob;
    return this.http.get(`${this.serverUrl}/listFilesTable4Url/${place}/${data2}/${data}`, options)
      .toPromise()
      .then(response =>response);
  }

  downloadFile_table5(data: string,data2: number,place: string):Promise<any>{
    const headers = new Headers({'Content-Type': 'application/json', 'Accept': '*'});
    const options = new RequestOptions({headers: headers});
    options.responseType = ResponseContentType.Blob;
    return this.http.get(`${this.serverUrl}/listFilesTable5Url/${place}/${data2}/${data}`, options)
      .toPromise()
      .then(response =>response);
  }

  downloadFile_table6(data: string,data2: number,place: string):Promise<any>{
    const headers = new Headers({'Content-Type': 'application/json', 'Accept': '*'});
    const options = new RequestOptions({headers: headers});
    options.responseType = ResponseContentType.Blob;
    return this.http.get(`${this.serverUrl}/listFilesTable6Url/${place}/${data2}/${data}`, options)
      .toPromise()
      .then(response =>response);
  }
}
