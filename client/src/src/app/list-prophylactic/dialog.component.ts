import {Component,OnInit, Inject, TemplateRef,HostListener,Input} from '@angular/core';
import {DOCUMENT} from '@angular/platform-browser';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
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
import {environment} from '../../environments/environment';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import {IMyDpOptions} from 'mydatepicker';
import { Resultsurvey} from '../model/list.mo';

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


 title:string = environment.title;
 tab1: string = environment.tab1;
 data_not_found: string = environment.data_not_found;
 tab2: string = environment.tab2;
 tab3: string = environment.tab3;
 surname: string = environment.surname;
 firstname : string = environment.firstname;
 lastname : string = environment.lastname;
 bithday : string = environment.bithday;
 telefon : string = environment.telefon;
 t_years : string = environment.t_years;
 start_date_etap1 : string = environment.start_date_etap1;
 end_date_etap1 : string = environment.end_date_etap1 ;
 start_date_etap2 : string = environment.start_date_etap2;
 end_date_etap2 : string = environment.end_date_etap2;
 ref_id_person : string = environment.ref_id_person;
 pm_god : string = environment.pm_god;
 pm_kvartal : string = environment.pm_kvartal;
 PM_HOSPITAL_RESULT : string = environment.PM_HOSPITAL_RESULT;
 adress : string = environment.adress ;
 tel : string = environment.tel;
 pm_result : string = environment.pm_result;
 close_card : string = environment.close_card;
 nstage : string = environment.nstage;
 dinfo : string = environment.dinfo ;
 tinfo : string = environment.tinfo;
 linksmo : string = environment.linksmo;
 _survey: string = environment.survey;
_linksmo_2: string = environment.linksmo_2;
_linksmo_1: string = environment.linksmo_1;
_linksmo_4: string = environment.linksmo_4;
_statsurvey1: string = environment.statsurvey1;
_statsurvey2: string = environment.statsurvey2;
_statsurvey3: string = environment.statsurvey3;
_statsurvey4: string = environment.statsurvey4;
_statsurvey5: string = environment.statsurvey5;
_statsurvey6: string = environment.statsurvey6;
_statsurvey7: string = environment.statsurvey7;
_statsurvey8: string = environment.statsurvey8;
_statsurvey9: string = environment.statsurvey9;
_statsurvey10: string = environment.statsurvey10;
_statsurvey11: string = environment.statsurvey11;
_statsurvey12: string = environment.statsurvey12;
_statsurvey13: string = environment.statsurvey13;
_statsurvey14: string = environment.statsurvey14;
_statsurvey15: string = environment.statsurvey15;
_statsurvey16: string = environment.statsurvey16;
_statsurvey17: string = environment.statsurvey17;
_statsurvey18: string = environment.statsurvey18;
_statsurvey19: string = environment.statsurvey19;
_statsurvey101: string = environment.statsurvey101;
_statsurvey102: string = environment.statsurvey102;
_statsurvey103: string = environment.statsurvey103;
_statsurvey104: string = environment.statsurvey104;
_statsurvey105: string = environment.statsurvey105;
_statsurvey106: string = environment.statsurvey106;
_statsurvey107: string = environment.statsurvey107;
_statsurvey108: string = environment.statsurvey108;
_statsurvey109: string = environment.statsurvey109;
_statsurvey110: string = environment.statsurvey110;
_statsurvey111: string = environment.statsurvey111;
_statsurvey20: string = environment.statsurvey20;
_statsurvey_result: string = environment.statsurvey_result;
_statsurvey_date: string = environment.statsurvey_date;
_titlesurvey: string = environment.titlesurvey;
_maintitlesurvey: string = environment.maintitlesurvey;
_otkreplen: string = environment.otkreplen;
_year_disp: string = environment.year_disp;
_error1: string = environment.error1;
  _error2: string = environment.error2;
  _error3: string = environment.error3;
  _error4: string = environment.error4;
  _error5: string = environment.error5;
  _error6: string = environment.error6;
  _error7: string = environment.error7;
  _error8: string = environment.error8;
  _error9: string = environment.error9;
  _error10: string = environment.error10;
  _error11: string = environment.error11;
  _error12: string = environment.error12;
  _resstat: string = environment.resstat;




 public resultsurvey: any = Resultsurvey;
 public myFormsurvey: FormGroup;
 public data_survey = [];
 public data_ger = [];
 public data_plan_informir;
 public data_informir = [];
 public selected=2017;
 public currentIndexPage = 0;
 public disableSelect: boolean = false;

  @Input() show:boolean = true;
  flag:boolean = true;
  flag_informed:boolean = true;
  flag_survey:boolean = true;
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

 constructor(public dialogRef: MatDialogRef<DialogComponent>,@Inject(MAT_DIALOG_DATA) public data: any,private personSearchIsurService: PeopleDatabase,private formBuilder: FormBuilder,) {
 console.log('ddddd '+JSON.stringify(data));
 }

 filterChanged(selectedValue: any){
   console.log('value is ',selectedValue.value);
   console.log('value is2 ',this.currentIndexPage);

		// ���
		if(this.currentIndexPage === 1){
		   this.show= true;
		   this.disableSelect = true;
		   let data_cust ={
				  surname: this.data.personSurname,
				  firstname:this.data.personKindfirstname,
				  lastname:this.data.personKindlastname,
				  bithday:this.data.personBirthday,
				  year:	selectedValue.value
				}
				this.personSearchIsurService.searchPersonGer(data_cust)
				.then(result =>{
						this.show= false;
						this.data_ger=result;
						this.disableSelect = false;

				});



		}
		// ��������������
		if(this.currentIndexPage === 2){

			this.disableSelect = true;
			this.flag_informed = true;

			let data_cust ={
		 	  surname: this.data.personSurname,
		 	  firstname: this.data.personKindfirstname,
		 	  lastname: this.data.personKindlastname,
		 	  bithday: this.data.personBirthday,
			  year:	selectedValue.value
		 	}

			// ��������� � �������
			this.personSearchIsurService.searchPersonInformir(data_cust)
			.then(result =>{
				this.disableSelect = false;
				this.data_informir = result;
	 			this.flag_informed = false;
			});


		}
   }

 check($event : any): void {


    this.currentIndexPage = $event.index;

 	if($event.index === 3){

	 	let data_cust ={
	 	  surname: this.data.personSurname,
	 	  firstname:this.data.personKindfirstname,
	 	  lastname:this.data.personKindlastname,
	 	  bithday:this.data.personBirthday
	 	}

	 	this.personSearchIsurService.searchPersonSurveyr(data_cust)
	 	.then(result =>{
	 	  this.flag_survey = false;
	 	  this.data_survey=result;

	 	  this.data_survey.length === 0 ? this.initform() : '';
	 	});
 	}
 	// tab '������ ���' have the index equal 1
 	if($event.index === 1){

		this.disableSelect = true;
 	    this.show= true;

	 	let data_cust ={
	 	  surname: this.data.personSurname,
	 	  firstname:this.data.personKindfirstname,
	 	  lastname:this.data.personKindlastname,
	 	  bithday:this.data.personBirthday,
		  year:this.selected
	 	}
	 	this.personSearchIsurService.searchPersonGer(data_cust)
	 	.then(result =>{
		 this.disableSelect = false;
	 	 this.show= false;
	 	  this.data_ger=result;
	 	});
 	}
 	// ��������������
 	if($event.index === 2){
		this.disableSelect = true;
	 	let data_cust ={
		 	  surname: this.data.personSurname,
		 	  firstname:this.data.personKindfirstname,
		 	  lastname:this.data.personKindlastname,
		 	  bithday:this.data.personBirthday,
			  year:	this.selected
		 	}


		this.personSearchIsurService.searchPersonInformir(data_cust)
	 	.then(result =>{

	 		this.personSearchIsurService.searchPlanPersonInformir('623375')
	 		.then(result_2 =>{
	 			this.data_plan_informir = result_2;

	 			this.data_informir = result;
	 			this.flag_informed = false;
				this.disableSelect = false;

	 			for (var index in this.data_informir) {
	 				   this.data_informir[index].nStage=== 0 ?  this.data_informir[index].nStage = environment.no_inform :
	 				   this.data_informir[index].nStage === 1 ?  this.data_informir[index].nStage = environment.one_part_inform :
	 				   this.data_informir[index].nStage === 2 ?  this.data_informir[index].nStage= environment.second_part_inform :
	 				   this.data_informir[index].nStage === 3 ?  this.data_informir[index].nStage = environment.secondory_inform : '';

	 				   this.data_informir[index].tInfo === 1 ?  this.data_informir[index].tInfo = environment.tinfo_1 :
	 				   this.data_informir[index].tInfo=== 2 ?  this.data_informir[index].tInfo= environment.tinfo_2 :
	 				   this.data_informir[index].tInfo === 3 ?  this.data_informir[index].tInfo = environment.tinfo_3 :
	 				   this.data_informir[index].tInfo === 4 ?  this.data_informir[index].tInfo = environment.tinfo_4 :
	 				   this.data_informir[index].tInfo === 5 ?  this.data_informir[index].tInfo = environment.tinfo_5 :
	 				   this.data_informir[index].tInfo === 6 ?  this.data_informir[index].tInfo = environment.tinfo_6 :
	 				   this.data_informir[index].tinfo === 7 ?  this.data_informir[index].tInfo = environment.tinfo_7 : '';

				}


	 		});


	 	});
 	}

 }


  add_survey(form: any){
	  // ������� null
	  for(let k in form.value) form.value[k]=== null ?form.value[k]='':form.value[k];
	  let str = "('','"+this.data.personSurname.toUpperCase( )+"','"+this.data.personKindfirstname.toUpperCase( )+"','"+this.data.personKindlastname+"','"+this.data.personBirthday+"','"+form.value.datesur.formatted+"','"+form.value.result_survey+"','"+form.value.prim+"','"+this.data.personLinksmoestablishmentid+"', SYSDATE, SYSDATE)";

	  this.personSearchIsurService.addResultSurvey(str)
		 	.then(result =>{
		 		this.flag_survey= true;
		 		 setTimeout(() => this.data_survey = [[this.data.personSurname,this.data.personKindfirstname,this.data.personKindlastname,form.value.datesur.jsdate,form.value.result_survey,'',this.data.personLinksmoestablishmentid]]
		 		 , 3000);

		 	});

	  //form.value.datesur != null ? (form.value.datesur.formatted  != undefined ? form.value.datesur = form.value.datesur.formatted : form.value.datesur = ''): form.value.datesur = '';
  }

  resetForm() {
  	  this.initform();
  }

initform() {
	    this.myFormsurvey =  this.formBuilder.group({
	      result_survey: ['', Validators.required],
	      datesur: ['', Validators.required],
	      prim: ['']

	    });
  }

   myDatePickerOptions: IMyDpOptions = {
        // other options...
        dateFormat: 'dd.mm.yyyy'

    }
}
