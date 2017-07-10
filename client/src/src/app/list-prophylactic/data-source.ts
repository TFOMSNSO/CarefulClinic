import {MdPaginator, MdSort} from '@angular/material';
import {DataSource} from '@angular/cdk';
import {Observable} from 'rxjs/Observable';
import {PeopleDatabase, UserData} from './people-database';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/map';

export class ProphylacticDataSource extends DataSource<any> {

 constructor(private _peopleDatabase: PeopleDatabase) {
    super();
  }
  
   connect(): Observable<UserData[]> {
    const displayDataChanges = [
    
    this._peopleDatabase.dataChange
    ];
    return Observable.merge(...displayDataChanges).map(() => {
      const data = this._peopleDatabase.data.slice();

      return data
    });
  }
  
  disconnect() {
    // No-op
  }
  
}