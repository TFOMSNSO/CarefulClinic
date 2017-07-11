import { Component,ViewChild,Output,ElementRef,EventEmitter} from '@angular/core';


@Component({
  moduleId: module.id,
  selector: 'table-header',
  templateUrl: './list-prophylactic.header.component.html',
  styleUrls: ['./list-prophylactic.header.component.scss']
  })
  
  
  
  export class ListProphylacticHeaderComponent {
  	@ViewChild('filter') filter: ElementRef;
  	@Output() filterOut: EventEmitter<any> = new EventEmitter<any>();
  	
  	getValue() {
  	
	    this.filterOut.emit(this.filter.nativeElement.value);
	}
  }