import {Component,OnInit, Inject, TemplateRef,HostListener,Input} from '@angular/core';
import {DOCUMENT} from '@angular/platform-browser';
import {MdDialog, MdDialogRef, MD_DIALOG_DATA} from '@angular/material';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/observable/fromEvent';
import { PeopleDatabase } from './people-database';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';

@Component({
  selector: 'app-list-prophylactic',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss'],
  animations: [
    trigger('toggle', [
      state('true', style({ opacity: 1, color: 'red' })),
      state('void', style({ opacity: 1, color: 'blue' })),
      //transition(':enter', animate('500ms ease-in-out')),
      //transition(':leave', animate('500ms ease-in-out'))
      transition(':enter', [
                style({
                    opacity: 0,
                    transform: 'translateX(-100%)'
                }),
                animate('2s ease-in')
            ]),
            transition(':leave', [
                animate('0s 0s ease-out', style({
                    opacity: 0,
                    transform: 'translateX(100%)'
                }))
            ])
    ])
  ]
})
export class DialogComponent{
 
 
 private title='\u041A\u0430\u0440\u0442\u043E\u0447\u043A\u0430';
 private tab1='\u0414\u0430\u043D\u043D\u044B\u0435 \u0420\u0421 \u0415\u0420\u0417';
 private tab2='\u0414\u0430\u043D\u043D\u044B\u0435 \u0413\u042D\u0420';
 private tab3='\u0418\u043D\u0444\u043E\u0440\u043C\u0438\u0440\u043E\u0432\u0430\u043D\u0438\u0435';
 private surname = '\u0424\u0430\u043C\u0438\u043B\u0438\u044F';
 private firstname = '\u0418\u043C\u044F';
 private lastname = '\u041E\u0442\u0447\u0435\u0441\u0442\u0432\u043E';
 private bithday = '\u0414\u0430\u0442\u0430 \u0440\u043E\u0436\u0434\u0435\u043D\u0438\u044F';
 private telefon = '\u0422\u0435\u043B\u0435\u0444\u043E\u043D';
 private t_years = '\u041F\u043E\u043B\u043D\u044B\u0445 \u043B\u0435\u0442';
 
 private start_date_etap1 = '\u0414\u0430\u0442\u0430 \u043D\u0430\u0447\u0430\u043B\u0430 \u043F\u0435\u0440\u0432\u043E\u0433\u043E \u044D\u0442\u0430\u043F\u0430';
 private end_date_etap1 = '\u0414\u0430\u0442\u0430 \u043E\u043A\u043E\u043D\u0447\u0430\u043D\u0438\u044F \u043F\u0435\u0440\u0432\u043E\u0433\u043E \u044D\u0442\u0430\u043F\u0430';
 private start_date_etap2 = '\u0414\u0430\u0442\u0430 \u043D\u0430\u0447\u0430\u043B\u0430 \u0432\u0442\u043E\u0440\u043E\u0433\u043E \u044D\u0442\u0430\u043F\u0430';
 private end_date_etap2 = '\u0414\u0430\u0442\u0430 \u043E\u043A\u043E\u043D\u0447\u0430\u043D\u0438\u044F \u0432\u0442\u043E\u0440\u043E\u0433\u043E \u044D\u0442\u0430\u043F\u0430';
 private ref_id_person = '\u0418\u0414 \u043F\u0430\u0446\u0438\u0435\u043D\u0442\u0430 \u0432 \u0418\u0421 \u0413\u042D\u0420';
 private pm_god = '\u0413\u043E\u0434 \u043F\u0440\u043E\u0445\u043E\u0436\u0434\u0435\u043D\u0438\u044F \u0434\u0438\u0441\u043F\u0430\u043D\u0441\u0435\u0440\u0438\u0437\u0430\u0446\u0438\u0438';
 private pm_kvartal = '\u041A\u0432\u0430\u0440\u0442\u0430\u043B \u043F\u0440\u043E\u0445\u043E\u0436\u0434\u0435\u043D\u0438\u044F \u0434\u0438\u0441\u043F\u0430\u043D\u0441\u0435\u0440\u0438\u0437\u0430\u0446\u0438\u0438';
 private PM_HOSPITAL_RESULT = '\u041A\u043E\u0434 \u041B\u041F\u0423';
 private adress = '\u0410\u0434\u0440\u0435\u0441 \u043F\u0430\u0446\u0438\u0435\u043D\u0442\u0430';
 private tel = '\u0422\u0435\u043B\u0435\u0444\u043E\u043D \u043F\u0430\u0446\u0438\u0435\u043D\u0442\u0430';
 private pm_result = '\u0420\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442 \u043F\u0440\u043E\u0445\u043E\u0436\u0434\u0435\u043D\u0438\u044F';
 private close_card = '\u0417\u0430\u043A\u0440\u044B\u0442\u044C';
 
 
 
 
 
 private data_ger ;
  @Input() show:boolean = true;
  flag:boolean = true;
  /*@HostListener('document:click')
  onClick(){
    this.show=!this.show;
  }*/

  animationStarted($event) {
    console.log('Start');
  }

  animationDone($event) {
    console.log('End');
  }
 
 constructor(public dialogRef: MdDialogRef<DialogComponent>,@Inject(MD_DIALOG_DATA) public data: any,private personSearchIsurService: PeopleDatabase) {
 //console.log('ddddd '+JSON.stringify(data));
 }
 
 check($event : any): void {
 	//console.log($event, null, 0);
 	
 	// tab 'Данные ГЭР' have the index equal 1
 	if($event.index === 1 && this.flag){
 	console.log('fff '+this.flag);
 	    this.flag =false;
	 	let data_cust ={
	 	  surname: this.data.personSurname,
	 	  firstname:this.data.personKindfirstname,
	 	  lastname:this.data.personKindlastname,
	 	  bithday:this.data.personBirthday
	 	}
	 	this.personSearchIsurService.searchPersonGer(data_cust)
	 	//.then(res => this.data_ger=res);
	 	.then(result =>{
	 	 // this.show = 'out';
	 	 this.show=!this.show;
	 	  this.data_ger=result;
	 	});
 	}
 	
 }
  
   
}
