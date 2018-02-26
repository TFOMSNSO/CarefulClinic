import { NgModule }            from '@angular/core';
import { CommonModule }        from '@angular/common';
import {MaterialModule} from './material-module';
import { MyDatePickerModule } from 'mydatepicker';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { Sp3Component } from './sp3.component';
import { ExpertiseComponent } from './expertise/expertise.component';


import { Sp3RoutingModule } from './sp3-routing.module';

import { Sp3Service } from './sp3.service';



@NgModule({
  imports: [ CommonModule, FormsModule, Sp3RoutingModule,MaterialModule,MyDatePickerModule,FormsModule,ReactiveFormsModule],
  declarations: [Sp3Component,ExpertiseComponent],
  providers:    [ Sp3Service]
})
export class Sp3Module { }