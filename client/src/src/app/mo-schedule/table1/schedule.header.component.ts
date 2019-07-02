import { Component,ViewChild,Output,ElementRef,EventEmitter} from '@angular/core';
import {environment} from '../../../environments/environment';


@Component({
  moduleId: module.id,
  selector: 'schedule-header',
  templateUrl: './schedule.header.component.html',
  styleUrls: ['./schedule.header.component.scss']
  })



  export class ScheduleHeaderComponent {
    search : string = environment.search_table;
  	@ViewChild('filter') filter: ElementRef;
  	@Output() filterOut: EventEmitter<any> = new EventEmitter<any>();

  	getValue() {
	    this.filterOut.emit(this.filter.nativeElement.value);
	  }
}
