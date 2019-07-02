import { Component,ViewChild,Output,ElementRef,EventEmitter} from '@angular/core';
import {environment} from '../../../environments/environment';


@Component({
  moduleId: module.id,
  selector: 'schedule-header4',
  templateUrl: './schedule4.header.component.html',
  styleUrls: ['./schedule4.header.component.scss']
  })



  export class Schedule4HeaderComponent {
    search : string = environment.search_table;
  	@ViewChild('filter') filter: ElementRef;
  	@Output() filterOut4: EventEmitter<any> = new EventEmitter<any>();

  	getValue() {
	    this.filterOut4.emit(this.filter.nativeElement.value);
	  }
}
