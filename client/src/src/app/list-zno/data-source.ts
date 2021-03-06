import {DataSource} from '@angular/cdk/collections';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/map';
import {PeopleZnoDatabaseService} from "./people-zno-database.service";
import {UserData} from "./people-zno-database.service";


export class ZnoDataSource extends DataSource<any>{
  _filterChange = new BehaviorSubject('');
  get filter(): string { return this._filterChange.value; }
  set filter(filter: string) {
    this._filterChange.next(filter);
  }

  constructor(public _peopleDatabase: PeopleZnoDatabaseService) {
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
