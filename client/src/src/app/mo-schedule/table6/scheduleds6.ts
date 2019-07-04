import {DataSource} from '@angular/cdk/collections';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/map';
import {ModatabaseService, moInfo6} from "../modatabase.service";

export class Scheduleds6 extends DataSource<any>{
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
      this.modatabase.dataChange6
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      let data = this.modatabase.data6.slice().filter((item: moInfo6) =>{
        let srhStr = item.lpuId;
        if(this.filter == '') return true;
        return srhStr == this.filter.trim();
        //return srhStr.indexOf(this.filter.trim()) != -1;
      });
      return data
    });
  }

  getDataFilter():moInfo6[]{
    return this.modatabase.data6.slice().filter((item: moInfo6) =>{
      let srhStr = item.lpuId;
      if(this.filter == '') return true;
      return srhStr == this.filter.trim();
      //return srhStr.indexOf(this.filter.trim()) != -1;
    });
  }

  disconnect(): void {
  }

}
