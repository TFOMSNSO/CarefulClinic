import { Component,ElementRef,ViewChild,Output, EventEmitter} from '@angular/core';
import { FormGroup, FormArray, FormBuilder, Validators,ValidationErrors, ValidatorFn } from '@angular/forms';
import { OnInit } from '@angular/core';
import {IMyDpOptions} from 'mydatepicker';
import {environment} from '../../../environments/environment';
import { ListMo,PmResalt } from '../../model/list.mo';
import { PeopleDatabase } from '../../list-prophylactic/people-database';
import { User } from '../../model/user';
import {PeopleZnoDatabaseService} from "../people-zno-database.service";

@Component({
  moduleId: module.id,
  selector: 'app-sidenav-search-zno-keys',
  templateUrl: './sidenav-search-zno-keys.component.html',
  styleUrls: ['./sidenav-search-zno-keys.component.css']
})

export class SidenavSearchZnoKeysComponent implements OnInit {
  public listmo: any =  ListMo;
  public pm_resalts: any =  PmResalt;
  public myForm: FormGroup;
  public currentUser: User;
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
  _survey: string = environment.survey;
  _nonrespond: string = environment.nonrespond;
  _respond: string = environment.respond;
  _survey_stat: string = environment.survey_stat;
  field_is_required: string = environment.field_is_required;
  _linksmo_2: string = environment.linksmo_2;
  _linksmo_1: string = environment.linksmo_1;
  _linksmo_4: string = environment.linksmo_4;
  _kv1: string = environment.kv1;
  _kv2: string = environment.kv2;
  _kv3: string = environment.kv3;
  _kv4: string = environment.kv4;
  _plandisp: string = environment.plandisp;
  _infoI: string = environment.infoI;
  _infoII: string = environment.infoII;
  _infoIII: string = environment.infoIII;

  @Output() progress_bar_emit: EventEmitter<any> = new EventEmitter<any>();
  @ViewChild('sidenavSearchKeys') variable_sidenave: any;
  constructor(private formBuilder: FormBuilder, private personSearchIsurService: PeopleZnoDatabaseService){}


  ngOnInit() {
    this.createForm();
  }



  createForm() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    //console.log(JSON.stringify(this.currentUser));
    this.myForm =  this.formBuilder.group({
      age :
        this.formBuilder.group({
            mansAge: [false],
            maleAge: [false],
            manAgemin: [''],
            manAgemax: [''],
            maleAgemin: [''],
            maleAgemax: ['']
          }),
      exportExcel:[false],
      currentUser:[this.currentUser['role'][0].id],
      count_notes:['', [Validators.required, Validators.min(1),Validators.max(5000000)]],
      simaz:[false],
      vtb:[false],
      ingos:[false],
    });

    //console.log('initiate form:'  + JSON.stringify(this.myForm.value));
  }




  searchPersonZnoKeys(form: any): void{
    //console.log('data to edit:' + JSON.stringify(form.value));
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    form.value.currentUser = this.currentUser.role[0].id;

    for(let k in form.value.age) form.value[k]=form.value.age[k];
    delete form.value.age;

    for(let k in form.value) form.value[k]=== null ?form.value[k]='':form.value[k];
    //console.log('value to be posed:' + JSON.stringify(form.value) );
    this.progress_bar_emit.emit({note: 'true', result:''});
    this.personSearchIsurService.searchPersonKeysZno(form.value)
      .then(res => {
        this.progress_bar_emit.emit({note :'false', result: res, search_keys: 'true'});
      });
  }


  open(){
    this.variable_sidenave.toggle(true);

    this.myForm.valueChanges.subscribe(data => {
      if(data.count_notes > 5000) data.exportExcel=true;
      //console.log('Form changes', data.count_notes+' - '+data.exportExcel);
    })
  }

  resetForm() {
    console.log('reset');
    this.myForm.reset();
  }

}
