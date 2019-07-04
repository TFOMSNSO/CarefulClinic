import {DataSource} from '@angular/cdk/collections';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/map';
import {ModatabaseService, moInfo4} from "../modatabase.service";


export class Scheduleds5 extends DataSource<any>{
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
      this.modatabase.dataChange5
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      let data = this.modatabase.data5.slice().filter((item: moInfo4) =>{
        let srhStr = item.lpuId;
        if(this.filter == '') return true;
        return srhStr == this.filter.trim();
        //return srhStr.indexOf(this.filter.trim()) != -1;
      });
      return data
    });
  }


  getDataFilter():moInfo4[]{
    return this.modatabase.data5.slice().filter((item: moInfo4) =>{
      let srhStr = item.lpuId;
      if(this.filter == '') return true;
      return srhStr == this.filter.trim();
      //return srhStr.indexOf(this.filter.trim()) != -1;
    });
  }

  disconnect(): void {
  }
}
