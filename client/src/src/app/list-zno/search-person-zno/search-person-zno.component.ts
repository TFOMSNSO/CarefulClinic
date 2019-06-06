import { Component, OnInit,ViewChild, Output, EventEmitter} from '@angular/core';
import {PeopleDatabase} from "../../list-prophylactic/people-database";
import {environment} from "../../../environments/environment";
import {SidenaveSearchService} from "../../list-prophylactic/sidenave-search.service";
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import {IMyDpOptions} from 'mydatepicker';


@Component({
  selector: 'app-search-person-zno',
  templateUrl: './search-person-zno.component.html',
  styleUrls: ['./search-person-zno.component.scss']
})
export class SearchPersonZnoComponent implements OnInit {
  reset: string = environment.reset;
  cancel: string = environment.cancel;
  field_is_required: string = environment.field_is_required;
  serch_form: string = environment.serch_form;
  surname: string = environment.surname;
  firstname : string = environment.firstname;
  lastname : string = environment.lastname;
  bithday : string = environment.bithday;
  public myForm: FormGroup;

  @ViewChild('sidenav') variable_sidenave: any;
  @Output() progress_bar_emit: EventEmitter<any> = new EventEmitter<any>();

  constructor(private sidenaveSearchService: SidenaveSearchService,private formBuilder: FormBuilder,private personSearchIsurService: PeopleDatabase){

  }

  ngOnInit(): void {
    this.myForm =  this.formBuilder.group({
      surname: ['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      bithday: ['', Validators.required]
    });
  }

  myDatePickerOptions: IMyDpOptions = {
    // other options...
    dateFormat: 'dd.mm.yyyy'

  };

  clearDate(): void {
    // Clear the date using the patchValue function
    this.myForm.patchValue({bithday: null});
  }



  open(){
    //this.variable_sidenave.nativeElement.open();
    console.log('sidenav.open()');
    this.variable_sidenave.toggle(true);
  }

  searchPerson(form: any): void{
    // �������� ������
    console.log('search Person SEARCH PERSON ZNO.ts ' + form.value.surname);
    this.progress_bar_emit.emit({note: 'true', result:''});

    form.value.bithday = form.value.bithday.formatted;
    //this.sidenaveSearchService.searchPersonGer(form.value);
    this.personSearchIsurService.searchPersonInsur(form.value)
      .then(result =>{
        this.progress_bar_emit.emit({note :'false', result: result});
      })
  }

  resetForm() {
    console.log('sidenav.search.component.ts resetForm()');
    this.myForm.reset();
  }

}
