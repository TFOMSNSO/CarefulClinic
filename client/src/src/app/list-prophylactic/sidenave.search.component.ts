import { Component,ElementRef,ViewChild } from '@angular/core';
import { SidenaveSearchService } from './sidenave-search.service';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import { OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  moduleId: module.id,
  selector: 'sidenav-component',
  templateUrl: './sidenave.search.component.html',
  styleUrls: ['./sidenave.search.component.scss'],
  providers: [SidenaveSearchService]
})


export class SadeaveSearchComponent   implements OnInit{

	public myForm: FormGroup;
	@ViewChild('sidenav') variable_sidenave: any;

	constructor(private sidenaveSearchService: SidenaveSearchService,private formBuilder: FormBuilder){}
	
	 ngOnInit() {
	    this.myForm =  this.formBuilder.group({
	      surname: ['', Validators.required],
	      firstname: ['', Validators.required],
	      lastname: ['', Validators.required],
	      bithday: ['']
	    });
  }

	
	
 open(){
 	//this.variable_sidenave.nativeElement.open();
 	console.log(this.variable_sidenave.open);
 	this.variable_sidenave.toggle(true);
 }
 
 searchPerson(form: NgForm): void{
  	this.sidenaveSearchService.searchPersonGer(form.value);
 }
 
 resetForm() {
  	  this.myForm.reset(); 
  	  
  }

}