import {MatPaginator, MatSort} from '@angular/material';
import {DataSource} from '@angular/cdk/collections';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';
import {PeopleDatabase, UserData} from '../list-prophylactic/people-database';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/map';

export class ZnoDataSource extends DataSource<any>{
  _filterChange = new BehaviorSubject('');
  get filter(): string { return this._filterChange.value; }
  set filter(filter: string) {
    this._filterChange.next(filter);
  }

  constructor(public _peopleDatabase: PeopleDatabase) {
    super();
  }

  connect(): Observable<any[]> {

    const displayDataChanges = [
      this._filterChange,
      this._peopleDatabase.dataChange
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      let data = this._peopleDatabase.data.slice().filter((item: UserData) =>{
        let srhStr = (item.personSurname + item.personKindfirstname).toLowerCase();
        return srhStr.indexOf(this.filter.toLowerCase()) != -1;
      });

      return data
    });
  }

  disconnect(): void {
  }

}
