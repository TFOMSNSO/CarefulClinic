import {DataSource} from '@angular/cdk/collections';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/map';
import {ModatabaseService, moInfo3} from "../modatabase.service";





export class Scheduleds3 extends DataSource<any>{
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
      this.modatabase.dataChange3//---------------------------
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      let data = this.modatabase.data3.slice().filter((item: moInfo3) =>{
        let srhStr = item.lpuId;
        if(this.filter == '') return true;
        return srhStr == this.filter.trim();
      });
      return data
    });
  }

  getDataFilter():moInfo3[]{
    return this.modatabase.data3.slice().filter((item: moInfo3) =>{
      let srhStr = item.lpuId;
      if(this.filter == '') return true;
      return srhStr == this.filter.trim();
    });
  }

  disconnect(): void {
  }
}
