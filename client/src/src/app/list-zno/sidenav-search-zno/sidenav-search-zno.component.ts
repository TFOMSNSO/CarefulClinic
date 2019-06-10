import { Component,ElementRef,ViewChild,Output, EventEmitter} from '@angular/core';
import { SidenaveSearchService} from "../../list-prophylactic/sidenave-search.service";
import { PeopleDatabase } from "../../list-prophylactic/people-database";
import { FormGroup, FormArray, FormBuilder, Validators } from '@angular/forms';
import { OnInit } from '@angular/core';
import {IMyDpOptions} from 'mydatepicker';
import { environment } from "../../../environments/environment";

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

  constructor(private sidenaveSearchService: SidenaveSearchService,private formBuilder: FormBuilder,private personSearchIsurService: PeopleDatabase){

  }

//еще не понял
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
    console.log('sidenav ZNO.open()111111');
    this.variable_sidenave.toggle(true);
  }
  //search zno person
  searchPerson(form: any): void{
    // �������� ������
    console.log('ZNOZNOZNO search Person sidenave.search.component.ts ' + JSON.stringify(form.value));

    this.progress_bar_emit.emit({note: 'true', result:''});

    form.value.bithday = form.value.bithday.formatted;
    //this.sidenaveSearchService.searchPersonGer(form.value);
    this.personSearchIsurService.searchPersonZNO(form.value)
      .then(result =>{
        this.progress_bar_emit.emit({note :'false', result: result});
      })
  }

  resetForm() {
    console.log('sidenav.search.ZNONZNONOOZNN.ts resetForm()');
    this.myForm.reset();
  }

}
