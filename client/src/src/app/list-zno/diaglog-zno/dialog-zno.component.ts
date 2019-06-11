import { Component, OnInit, Input, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import {IMyDpOptions} from 'mydatepicker';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/observable/fromEvent';

import {environment} from "../../../environments/environment";
import {Resultsurvey} from "../../model/list.mo";
import { PeopleZnoDatabaseService } from "../people-zno-database.service";
import {DialogComponent} from "../../list-prophylactic/dialog.component";

@Component({
  selector: 'app-dialog-zno',
  templateUrl: './dialog-zno.component.html',
  styleUrls: ['./dialog-zno.component.scss'],
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

export class DialogZnoComponent {
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

  constructor(public dialogRef: MatDialogRef<DialogZnoComponent>,private peopleDatabase : PeopleZnoDatabaseService, @Inject(MAT_DIALOG_DATA) public data: any,private formBuilder : FormBuilder) { }


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
      this.peopleDatabase.searchPersonGer(data_cust)
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
      this.peopleDatabase.searchPersonInformir(data_cust)
        .then(result =>{
          this.disableSelect = false;
          this.data_informir = result;
          this.flag_informed = false;
        });


    }
  }
}


