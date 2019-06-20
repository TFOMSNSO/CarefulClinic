import { Component, OnInit, Input, Inject } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatTabChangeEvent} from '@angular/material';
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
        animate('1s ease-in')
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
  tab2: string = environment.title_treat;
  tab3: string = environment.tab3;
  tab4:string = environment.sp3_menu_expertisa;
  surname: string = environment.surname;
  firstname : string = environment.firstname;
  lastname : string = environment.lastname;
  bithday : string = environment.bithday;

  telefon : string = environment.telefon;
  t_years : string = environment.t_years;
  address : string = environment.adress ;
  tel : string = environment.tel;
  pm_result : string = environment.pm_result;
  close_card : string = environment.close_card;
  nstage : string = environment.nstage;
  dinfo : string = environment.dinfo ;
  tinfo : string = environment.tinfo;
  linksmo : string = environment.linksmo;
  _linksmo_2: string = environment.linksmo_2;
  _linksmo_1: string = environment.linksmo_1;
  _linksmo_4: string = environment.linksmo_4;
  _otkreplen: string = environment.otkreplen;

  about: string = environment.about;

  //лечение
  treat_type: string = environment.treat_types;
  t_date_begin: string = environment.date_begin;
  t_date_end: string = environment.date_end;
  mkb: string = environment.mkb;
  mes_ksg :string = environment.mes_ksg;
  no_treatment_types:string = environment.no_treatment;
  lpu_name:string = environment.lpu_name;
  ot_profk:string = environment.ot_profk;
  treats_data: any;

  //экспертиза
  title_exp: string = environment.title_exp;
  nom_polis:string = environment.nom_polis;
  ser_polis:string = environment.ser_polis;
  expert: string = environment.name_exp;
  sum: string = environment.summa;
  shtraf: string = environment.shtraf;
  no_expertise: string = environment.no_expertise_found;
  doct_name: string = environment.doct_name;
  exam_date: string = environment.exam_date;
  exam_code: string = environment.exam_code;
  lpu_mes: string = environment.lpu_mes;
  lpu_mlb: string = environment.lpu_mkb;
  exp_mes: string = environment.exp_mes;
  cod_exp: string = environment.cod_exp;
  def_main: string = environment.def_main;



  public resultsurvey: any = Resultsurvey;
  public myFormsurvey: FormGroup;
  //public data_treatment:any ;
  public currentIndexPage = 0;


  @Input() show:boolean = true;
  flag:boolean = true;
  flag_informed:boolean = true;
  flag_survey:boolean = true;

  constructor(public dialogRef: MatDialogRef<DialogZnoComponent>,private peopleDatabase : PeopleZnoDatabaseService, @Inject(MAT_DIALOG_DATA) public data: any) { }


  resetForm() {

  }


  myDatePickerOptions: IMyDpOptions = {
    // other options...
    dateFormat: 'dd.mm.yyyy'

  }

  check(event : MatTabChangeEvent): void {
    //this.currentIndexPage = event.index;
   // console.log('currentindex:' + this.currentIndexPage);
   // console.log('show:' + this.show);

    if(event.index === 1){
      this.show = true;
      let id_cust = this.data.id1;
      this.peopleDatabase.searchTreatment(id_cust).then(res => {
        this.treats_data = res;
        //console.log(JSON.stringify(res));
        for(let i:number=0;i < res.length; i++){
          this.findexp(res[i].dateBegin,res[i].dateEnd,this.data).then(r =>{
            this.treats_data[i].expertise = r;
            //if(r != null)
              //console.log('expertise:' + JSON.stringify(this.treats_data[i].expertise));
          });
        }

        for(let i:number=0;i < this.treats_data.length; i++) {
          this.makeTreatment(this.treats_data[i]);
        }

        this.show = false;
      });
    }
  }


  //записываем типы лечения
  makeTreatment(data:any){
    let treat_types = [];
    var i:number = 0;
    if(data.lt1 == '1') {treat_types[i] = environment.treat_type1; i++}
    if(data.lt2 == '1') {treat_types[i] = environment.treat_type2; i++}
    if(data.lt3 == '1') {treat_types[i] = environment.treat_type3; i++}
    if(data.lt4 == '1') {treat_types[i] = environment.treat_type4; i++}
    if(data.lt5 == '1') {treat_types[i] = environment.treat_type5; i++}
    if(data.lt6 == '1') {treat_types[i] = environment.treat_type6;}
    data.treat_types = treat_types;
  }


  //ищем экспертизу по каждому лечению
  findexp(dateBegin:any,dateEnd:any,person:any):Promise<any>{
    let resp: any = null;
    let p:any = {};
    p.surname = person.personSurname;
    p.firstname = person.personKindfirstname;
    p.lastname = person.personKindlastname;
    p.bithday = person.personBirthday;
    p.dateBegin = dateBegin;
    p.dateEnd = dateEnd;

    return this.peopleDatabase.findExpertise(p);

  }
}


