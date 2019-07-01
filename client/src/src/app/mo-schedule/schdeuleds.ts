import {DataSource} from '@angular/cdk/collections';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/map';
import {ModatabaseService, moInfo} from "./modatabase.service";






export class Schdeuleds extends DataSource<any>{
  _filterChange = new BehaviorSubject('');
  get filter(): string { return this._filterChange.value; }
  set filter(filter: string) {
    this._filterChange.next(filter);
  }

  constructor(public modatabase: ModatabaseService){
    super();
  }

  connect(): Observable<any[]> {
    const displayDataChanges = [
      this._filterChange,
      this.modatabase.dataChange
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      let data = this.modatabase.data.slice().filter((item: moInfo) =>{
        let srhStr = item.lpuId;
        return srhStr.indexOf(this.filter) != -1;
      });

      return data
    });
  }

  disconnect(): void {
  }
}
