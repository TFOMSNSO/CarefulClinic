import { Component,ViewChild,Output,ElementRef,EventEmitter} from '@angular/core';
import {environment} from '../../environments/environment';


@Component({
  moduleId: module.id,
  selector: 'table-header',
  templateUrl: './list-prophylactic.header.component.html',
  styleUrls: ['./list-prophylactic.header.component.scss']
  })
  
  
  
  export class ListProphylacticHeaderComponent {
    search : string = environment.search_table;
  	@ViewChild('filter') filter: ElementRef;
  	@Output() filterOut: EventEmitter<any> = new EventEmitter<any>();
  	
  	getValue() {
  	
	    this.filterOut.emit(this.filter.nativeElement.value);
	}
  }