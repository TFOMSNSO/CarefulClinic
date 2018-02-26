import { Injectable, OnDestroy } from '@angular/core';
import { ListFiles } from '../model/list.files';
import { Http, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import {environment} from '../../environments/environment';



@Injectable()
export class AdminService{

  serverUrl : string = environment.BACKEND_URL + "/rest/admin";
	
  constructor(private http: Http) { console.log('ContactService instance created.'); }
  
  /* Загрузка сформированных файлов. Информирование о втором этапе*/
  
  listFiles(): Promise<ListFiles[]> {
  let headers = new Headers({'Content-Type': 'application/json'});
    return this.http.get(`${this.serverUrl}/listFiles`,{headers: headers})
               .toPromise()
               .then(response => response.json() as ListFiles[]);
  }
  
	listFilesInformingBrowser(p_path: string): Promise<ListFiles[]>{
		let headers = new Headers({'Content-Type': 'application/json'});
			return this.http
			  .post(this.serverUrl + '/listFilesBrowser', JSON.stringify(p_path), {headers: headers})
			  .debounceTime(500)
			  .toPromise()
			  
			  .then(response => response.json() as ListFiles[]);
	}
  
  
}
