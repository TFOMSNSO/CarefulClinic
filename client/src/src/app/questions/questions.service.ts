import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {environment} from '../../environments/environment';

import { Question } from '../model/question';

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
}