import { Component,ElementRef,ViewChild,Output, EventEmitter} from '@angular/core';
//import { SidenaveSearchService } from './sidenave-search.service';
//import { PeopleDatabase } from './people-database';
import { FormGroup, FormArray, FormBuilder, Validators,ValidationErrors, ValidatorFn } from '@angular/forms';
import { OnInit } from '@angular/core';
import {IMyDpOptions} from 'mydatepicker';
import {environment} from '../../../environments/environment';
import { ListMo,PmResalt } from '../../model/list.mo';
import { PeopleDatabase } from '../people-database';
import { User } from '../../model/user';

@Component({
  moduleId: module.id,
  selector: 'sidenavkeys-component',
  templateUrl: './sidenave.search.keys.component.html',
  styleUrls: ['./sidenave.search.keys.component.scss']
  
})


export class SadeaveSearchKeysComponent   implements OnInit{
	
	 
	public listmo: any =  ListMo;
	private pm_resalts: any =  PmResalt;
	public myForm: FormGroup;
	 private currentUser: User;
	 _searchKEYS: string = environment.searchKEYS;
	 _age: string = environment.age;
	_sexmans: string = environment.sexmans;
	_sexmale: string = environment.sexmale;
	_ex: string = environment.ex;
	_through: string = environment.through;
	_mo: string = environment.mo;
	_motype: string = environment.motype;
	_withtel: string = environment.withtel;
	_inform: string = environment.inform;
	_I: string = environment.I;
	_II: string = environment.II;
	_III: string = environment.III;
	_sms: string = environment.sms;
	_telsearchkeys: string = environment.telsearchkeys;
	_email: string = environment.email;
	_mail: string = environment.mail;
	_personaly: string = environment.personaly;
	_notfound: string = environment.notfound;
	_doneexamination: string = environment.doneexamination;
	_periodinf: string = environment.periodinf;
	_dispanser: string = environment.dispanser;
	_statedisp: string = environment.statedisp;
	_causenotes: string = environment.causenotes;
	_exportExcel: string = environment.exportExcel;
	reset: string = environment.reset;
	cancel: string = environment.cancel;
	
	
	
	 
	 
	
	
	@ViewChild('sidenavSearchKeys') variable_sidenave: any;
	constructor(private formBuilder: FormBuilder, private personSearchIsurService: PeopleDatabase){}
	
	
  createForm() {
  this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  this.myForm =  this.formBuilder.group({
	      age :  
								      this.formBuilder.group({
								      	mansAge: [false],
								      	maleAge: [false],
								      	manAgemin: [''],
								        manAgemax: [''],
									    maleAgemin: [''],
									    maleAgemax: ['']
								      }
								      ,{ validator: this.atLeastOne2(Validators.required) }),
	      typeMo: [''],
	      nullstate: [false],
	      nullmedicalexamination_conducted: [false],
	      firstdatenullstate: [''],
	      seconddatenullstate: [''],
	      firststate: [false],
	      firsttel: [''],
	      firstsms: [''],
	      firstemail: [''],
	      firstmail: [''],
	      firstpersonal_information: [''],
	      firstinforming_inadmissible: [''],
	      firstmedicalexamination_conducted: [''],
	      firstdatefirststate: [''],
	      seconddatefirststate: [''],
	      secondstate: [''],
	      secondtel: [''],
	      secondsms: [''],
	      secondemail: [''],
	      secondmail: [''],
	      secondpersonal_information: [''],
	      secondinforming_inadmissible: [''],
	      secondmedicalexamination_conducted: [''],
	      firstdatesecondstate: [''],
	      seconddatesecondstate: [''],
	      thridstate: [''],
	      thridtel: [''],
	      thridsms: [''],
	      thridemail: [''],
	      thridmail: [''],
	      thridpersonal_information: [''],
	      thridinforming_inadmissible: [''],
	      thridmedicalexamination_conducted: [''],
	      firstdatethridstate: [''],
	      seconddatethridstate: [''],
	      ger :  
								      this.formBuilder.group({
								      	pm_result:[''],
									   start_date_etap1_dsp:[''],
									    end_date_etap1_dsp:['']
								      }
								      ,{ validator: this.atLeastOne3(Validators.required) }),
	      isTelefon:[''],
	      exportExcel:[false],
	      currentUser:[this.currentUser['role'][0].id],
	      count_notes:['', Validators.required]
	    });
  }
  
  atLeastOne3 = (validator: ValidatorFn) => (group: FormGroup): ValidationErrors | null => {
  
  	if((group.controls.pm_result.value === 2 || group.controls.pm_result.value === 5) && ((group.controls.start_date_etap1_dsp.value != '' && group.controls.start_date_etap1_dsp.value != null) || (group.controls.end_date_etap1_dsp.value != '' && group.controls.end_date_etap1_dsp.value != null))){
  		if((group.controls.start_date_etap1_dsp.value !='' && group.controls.start_date_etap1_dsp.value != null) && (group.controls.end_date_etap1_dsp.value !='' && group.controls.end_date_etap1_dsp.value != null )){
  			return null;
  		}else{
  			return { atLeastOne: true};
  		}
  	
  	}else{
  		return null;
  	}
 };
 
 atLeastOne2 = (validator: ValidatorFn) => (group: FormGroup): ValidationErrors | null => {
 	let tmp1 = 0, tmp2 = 0; 
 	if(group.controls.mansAge.value){
	  	if(group.controls.manAgemin.value && group.controls.manAgemax.value){
		  	tmp1 = 0;
	  	 }else{
	  		tmp1 = 1;
	  	 }
  	 }
  	 
  	 if(group.controls.maleAge.value){
	  	if(group.controls.maleAgemin.value && group.controls.maleAgemax.value){
		  	tmp2 = 0;	
	  	 }else{
	  		tmp2 = 1;
	  	 }
  	}
  	if((tmp1+tmp2) > 0){return { atLeastOne: true};}else{return null;}
 };
	
	ngOnInit() {
	    this.createForm();
  }
  
    myDatePickerOptions: IMyDpOptions = {
        // other options...
        dateFormat: 'dd.mm.yyyy'
        
    };
    
     resetForm() {
     	this.createForm();
	 }
  
   searchPersonKeys(form: any): void{
   // удаляем вложенный объект, предварительно скопировав содержимое в объект родитель
   for(let k in form.value.age) form.value[k]=form.value.age[k];
	delete form.value.age;
	for(let k in form.value.ger) form.value[k]=form.value.ger[k];
	delete form.value.ger;
	// убираем null
	for(let k in form.value) form.value[k]=== null ?form.value[k]='':form.value[k];
	   
	// форматируем дату в dd.mm.yyyy
	 form.value.firstdatenullstate != null ? (form.value.firstdatenullstate.formatted  != undefined ? form.value.firstdatenullstate = form.value.firstdatenullstate.formatted : form.value.firstdatenullstate = ''): form.value.firstdatenullstate = '';  	 
	 form.value.seconddatenullstate != null ? (form.value.seconddatenullstate.formatted != undefined ? form.value.seconddatenullstate = form.value.seconddatenullstate.formatted : form.value.seconddatenullstate = ''): form.value.seconddatenullstate = '';
	 form.value.firstdatefirststate != null ? (form.value.firstdatefirststate.formatted  != undefined ? form.value.firstdatefirststate = form.value.firstdatefirststate.formatted : form.value.firstdatefirststate = ''): form.value.firstdatefirststate = '';  	 
	 form.value.seconddatefirststate != null ? (form.value.seconddatefirststate.formatted != undefined ? form.value.seconddatefirststate = form.value.seconddatefirststate.formatted : form.value.seconddatefirststate = ''): form.value.seconddatefirststate = '';
	 form.value.firstdatesecondstate != null ? (form.value.firstdatesecondstate.formatted != undefined ? form.value.firstdatesecondstate = form.value.firstdatesecondstate.formatted : form.value.firstdatesecondstate = ''): form.value.firstdatesecondstate = '';  	 
	 form.value.seconddatesecondstate != null ? (form.value.seconddatesecondstate.formatted != undefined ? form.value.seconddatesecondstate = form.value.seconddatesecondstate.formatted : form.value.seconddatesecondstate = ''): form.value.seconddatesecondstate = '';
	 form.value.firstdatethridstate != null ? (form.value.firstdatethridstate.formatted != undefined ? form.value.firstdatethridstate = form.value.firstdatethridstate.formatted : form.value.firstdatethridstate = ''): form.value.firstdatethridstate = '';  	 
	 form.value.seconddatethridstate != null ? (form.value.seconddatethridstate.formatted != undefined ? form.value.seconddatethridstate = form.value.seconddatethridstate.formatted : form.value.seconddatethridstate = ''): form.value.seconddatethridstate = '';
	 form.value.start_date_etap1_dsp != null ? (form.value.start_date_etap1_dsp.formatted != undefined ? form.value.start_date_etap1_dsp = form.value.start_date_etap1_dsp.formatted : form.value.start_date_etap1_dsp = ''): form.value.start_date_etap1_dsp = '';
	 form.value.end_date_etap1_dsp != null ? (form.value.end_date_etap1_dsp.formatted != undefined ? form.value.end_date_etap1_dsp = form.value.end_date_etap1_dsp.formatted : form.value.end_date_etap1_dsp = ''): form.value.end_date_etap1_dsp = '';
	 
	console.log(form.value); 
 	 this.personSearchIsurService.searchPersonKeys(form.value)
 }
  

 open(){
 	//this.variable_sidenave.nativeElement.open();
 	console.log(this.variable_sidenave.open);
 	console.log("dfsfsdf");
 	this.variable_sidenave.toggle(true);
 }

}