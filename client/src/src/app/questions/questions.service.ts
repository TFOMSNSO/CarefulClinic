import { Injectable } from '@angular/core';
import { Http, Headers } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {environment} from '../../environments/environment';


import { Question } from '../model/question.interface';

@Injectable()
export class QuestionsService {

  serverUrl : string = environment.BACKEND_URL + "/rest/questions";
  constructor(private http: Http){}

  getAllQuestions(): Promise<Question[]> {
    return this.http.get(this.serverUrl)
               .toPromise()
               .then(response => response.json() as Question[])
               //.catch(this.handleError);
  }

    
	createResponse(question: any): Promise<any> {
	let headers = new Headers();
	 console.log(JSON.stringify(question));
	return this.http
	  .post(this.serverUrl + '/add', JSON.stringify(question), {headers: headers})
	  .toPromise()
	  .then(res => res.json())
	  //.catch(this.handleError);
  }
  
}