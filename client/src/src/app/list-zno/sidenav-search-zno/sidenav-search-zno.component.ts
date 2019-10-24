import { Component,ElementRef,ViewChild,Output, EventEmitter} from '@angular/core';
import { SidenaveSearchService} from "../../list-prophylactic/sidenave-search.service";
import { PeopleDatabase } from "../../list-prophylactic/people-database";
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import { OnInit } from '@angular/core';
import {IMyDpOptions} from 'mydatepicker';
import { environment } from "../../../environments/environment";
import {PeopleZnoDatabaseService} from "../people-zno-database.service";

@Component({
  moduleId: module.id,
  selector: 'sidenav-search-zno',
  templateUrl: './sidenav-search-zno.component.html',
  styleUrls: ['./sidenav-search-zno.component.scss'],
  providers: [SidenaveSearchService]
})
export class SidenavSearchZnoComponent implements OnInit {

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

  constructor(private sidenaveSearchService: SidenaveSearchService,private formBuilder: FormBuilder,private personSearchIsurService: PeopleZnoDatabaseService){

  }

  ngOnInit() {
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
    this.variable_sidenave.toggle(true);
  }
  //search zno person
  searchPerson(form: any): void{
    let smo = JSON.parse(localStorage.getItem('currentUser'));
    this.progress_bar_emit.emit({note: 'true', result:''});
    form.value.smo = smo['role'][0].id;
    form.value.bithday = form.value.bithday.formatted;
    console.log('form.value = ' + JSON.stringify(form.value));
    this.personSearchIsurService.searchPersonZNO(form.value)
      .then(result =>{
        this.progress_bar_emit.emit({note :'false', result: result});
      })
  }

  resetForm() {
    this.myForm.reset();
  }

}
