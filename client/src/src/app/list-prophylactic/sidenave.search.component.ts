import { Component,ElementRef,ViewChild } from '@angular/core';

@Component({
  moduleId: module.id,
  selector: 'sidenav-component',
  templateUrl: './sidenave.search.component.html',
  styleUrls: ['./sidenave.search.component.scss']
})


export class SadeaveSearchComponent{

	@ViewChild('sidenav') variable_sidenave: any;
	
 open(){
 	//this.variable_sidenave.nativeElement.open();
 	console.log(this.variable_sidenave.open);
 	this.variable_sidenave.toggle(true);
 	
 }

}