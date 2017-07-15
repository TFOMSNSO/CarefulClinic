import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {environment} from '../../environments/environment';

@Injectable()
export class SidenaveSearchService {

  serverUrl : string = environment.BACKEND_URL + "/rest/prophylactic";
  constructor(private http: Http) { }
  
 searchPersonGer(per_data: any): Promise<any> {
	let headers = new Headers({'Content-Type': 'application/json'});
	 console.log(JSON.stringify(per_data));
	return this.http
	  .post(this.serverUrl + '/search_person_ger', JSON.stringify(per_data), {headers: headers})
	  .toPromise()
	  .then(res => console.log('test form' +res.json()))
  } 

}
