import { Component} from '@angular/core';
import {MdDialog, MdDialogRef} from '@angular/material';

@Component({
  selector: 'app-list-prophylactic',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.scss']
})
export class DialogComponent {
 constructor(public dialogRef: MdDialogRef<DialogComponent>) {}
  
}
