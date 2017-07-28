import { Component, OnInit, Inject, ViewChild, TemplateRef,ElementRef } from '@angular/core';
import {PeopleDatabase, UserData} from './people-database';
import {ProphylacticDataSource} from './data-source';
import {MdPaginator} from '@angular/material';
import {MdSort} from '@angular/material';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import { DialogComponent } from './dialog.component';
import {DOCUMENT} from '@angular/platform-browser';
import {MdDialog, MdDialogRef, MD_DIALOG_DATA} from '@angular/material';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/observable/fromEvent';
import {MdSnackBar} from '@angular/material';
import {environment} from '../../environments/environment';


export type UserProperties = 'personSurname' | 'personKindfirstname' | 'personKindlastname' | 'personBirthday' | 'personYears' | 'edit' | undefined;


@Component({
  moduleId: module.id,
  selector: 'app-list-prophylactic',
  templateUrl: './list-prophylactic.component.html',
  styleUrls: ['./list-prophylactic.component.scss'],
  animations: [

    trigger('questionsAnim', [
      transition('* => *', [
        query('.full-width,.test', style({ opacity: 0 }), {optional: true}),

        query('.full-width,.test', stagger('1400ms', [
          animate('1s ease-in', keyframes([
            style({opacity: 0, transform: 'translateY(-100%)', offset: 0}),
            style({opacity: 0, transform: 'translateY(500px)',  offset: 0.3}),
            style({opacity: 1, transform: 'translateY(0)',     offset: 1.0}),
          ]))]), {optional: true}),
        
      ])
      
    ]),
    
    trigger('flyInOut', [
            state('in', style({ opacity: 1, transform: 'translateX(0)' })),
            transition('void => *', [
                style({
                    opacity: 0,
                    transform: 'translateX(-100%)'
                }),
                animate('0.6s ease-in')
            ]),
            transition('* => void', [
                animate('0.2s 0.1s ease-out', style({
                    opacity: 0,
                    transform: 'translateX(100%)'
                }))
            ])
        ])

  ]
  
})
export class ListProphylacticComponent implements OnInit {
   t_years: string = environment.t_years;
  lists_insur: string = environment.lists_insur;
  surname: string = environment.surname;
  firstname: string = environment.firstname;
  lastname: string = environment.lastname;
  bithday: string = environment.bithday;
  action_add_person = environment.action_add_person;
  add_table = environment.add_table;
  bad_action_add_person = environment.bad_action_add_person;
  
  progress_bar: boolean = false;
  dataSource: ProphylacticDataSource | null;
  displayedColumns: UserProperties[] = [];
  constructor(public _peopleDatabase: PeopleDatabase,
  				  public dialog: MdDialog,
  				  public snackBar: MdSnackBar) { }
  dialogRef: MdDialogRef<DialogComponent> | null;
  
  
	getNotify(note:string): void{
		this.dataSource.filter = note;
	}
	
	handleProgressUpdated($event):void{
	   this.progress_bar = JSON.parse($event.note);
	   
	   // false - флаг закрытия прогресс бара; 0/1 - результат поиска в РС ЕРЗ 
	   if($event.note === 'false' && $event.result !== 0){
			   this.snackBar.open(this.action_add_person,this.add_table, {
		    		 duration: 3000,
		   		 });
    	 }
    	 if($event.note === 'false' && $event.result === 0){
			   this.snackBar.open(this.bad_action_add_person,undefined, {
		    		 duration: 4000,
		   		 });
    	 }
	}
  

  ngOnInit() {
    this.connect();
     /*Observable.fromEvent(this.filter.nativeElement, 'keyup')
        .debounceTime(150)
        .distinctUntilChanged()
        .subscribe(() => {
          if (!this.dataSource) { return; }
         this.dataSource.filter = this.filter.nativeElement.value;
         
        });*/
  }

  connect() {
    this.displayedColumns = ['personSurname','personKindfirstname','personKindlastname','personBirthday','personYears','edit'];
    this.dataSource = new ProphylacticDataSource(this._peopleDatabase);
    this._peopleDatabase.initialize();
  }
  
  
  
  //execute dialog
  preview(pr:any):void{
	let cc = {
				disableClose: true,
			    panelClass: 'custom-overlay-pane-class',
			    hasBackdrop: true,
			    backdropClass: '',
			    width: '70%',
			    height: '500px',
			    position: {
			      top: '',
			      bottom: '',
			      left: '',
			      right: ''
			    },
				data: pr
	}  
   this.dialogRef = this.dialog.open(DialogComponent,cc);
   // dialogRef.afterClosed().subscribe(result => {
     // this.selectedOption = result;
    //});
   
   let result = JSON.stringify(pr, function(key, val) {
    if (key !== "edit")
        return val;
	});
	
  }


}
