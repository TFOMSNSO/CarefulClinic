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
    console.log('createForm() search zno keys');
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
      no_firststate: [false],
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
      no_secondstate: [false],
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
      no_thridstate: [false],
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
      count_notes:['', [Validators.required, Validators.min(1),Validators.max(5000000)]],
      survey_stat:[''],
      simaz:[false],
      vtb:[false],
      ingos:[false],
      plan_disp:[0]
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


  searchPersonKeys(form: any): void{
    // ������� ��������� ������, �������������� ���������� ���������� � ������ ��������
    for(let k in form.value.age) form.value[k]=form.value.age[k];
    delete form.value.age;
    for(let k in form.value.ger) form.value[k]=form.value.ger[k];
    delete form.value.ger;
    // ������� null
    for(let k in form.value) form.value[k]=== null ?form.value[k]='':form.value[k];

    // ����������� ���� � dd.mm.yyyy
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

    //console.log(form.value);
    this.progress_bar_emit.emit({note: 'true', result:''});
    this.personSearchIsurService.searchPersonKeysZno(form.value)
      .then(res => {
        this.progress_bar_emit.emit({note :'false', result: res, search_keys: 'true'});
      });
    let r=[];
    //
  }


  open(){
    console.log('search zno keys');
    //this.variable_sidenave.nativeElement.open();
    this.variable_sidenave.toggle(true);

    this.myForm.valueChanges.subscribe(data => {
      //console.log('Form changes', data)
      if(data.count_notes > 5000) data.exportExcel=true;
      console.log('Form changes', data.count_notes+' - '+data.exportExcel);
    })
  }

}
