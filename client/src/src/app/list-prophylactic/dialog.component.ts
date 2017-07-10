import {Component, Inject, ViewChild, TemplateRef} from '@angular/core';
import {MdDialog, MdDialogRef, MD_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'app-list-prophylactic',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss']
})
export class DialogComponent {
 constructor(public dialogRef: MdDialogRef<DialogComponent>,@Inject(MD_DIALOG_DATA) public data: any) {
 console.log('ddddd '+data);
 }
  
}
