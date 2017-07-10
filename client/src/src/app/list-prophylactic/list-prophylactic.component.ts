import { Component, OnInit, Inject, ViewChild, TemplateRef } from '@angular/core';
import {PeopleDatabase, UserData} from './people-database';
import {ProphylacticDataSource} from './data-source';
import {MdPaginator} from '@angular/material';
import {MdSort} from '@angular/material';
import { trigger,style,transition,animate,keyframes,query,stagger, state } from '@angular/animations';
import { DialogComponent } from './dialog.component';
import {DOCUMENT} from '@angular/platform-browser';
import {MdDialog, MdDialogRef, MD_DIALOG_DATA} from '@angular/material';


export type UserProperties = 'userId' | 'userName' | 'progress' | 'color' | 'edit' | undefined;

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

  dataSource: ProphylacticDataSource | null;
  displayedColumns: UserProperties[] = [];
  constructor(public _peopleDatabase: PeopleDatabase,public dialog: MdDialog) { }
  dialogRef: MdDialogRef<DialogComponent> | null;
  configcc = {
    data: {
      message: 'Jazzy jazz jazz'
    }
  }
  
  
  

  ngOnInit() {
    this.connect();
  }

  connect() {
    this.displayedColumns = ['userId', 'userName', 'progress', 'color', 'edit'];
    this.dataSource = new ProphylacticDataSource(this._peopleDatabase);
    this._peopleDatabase.initialize();
  }
  
  preview(pr:any):void{
	let cc = {data: pr}  
   this.dialogRef = this.dialog.open(DialogComponent,cc);
   // dialogRef.afterClosed().subscribe(result => {
     // this.selectedOption = result;
    //});
   
   let result = JSON.stringify(pr, function(key, val) {
    if (key !== "edit")
        return val;
	});
	
	console.log('test\n'+result);
  }


}
