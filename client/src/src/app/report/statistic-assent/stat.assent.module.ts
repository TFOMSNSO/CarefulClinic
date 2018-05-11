import { NgModule }            from '@angular/core';
import { CommonModule }        from '@angular/common';
import {MaterialModule} from './material-module';
import { MyDatePickerModule } from 'mydatepicker';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

/* Компоненты родители */
import { StatisticsAssentComponent } from './stat.assent.component';

/* Дочерние компоненты */
import { ReportStatisticsAssentComponent } from './reports/report.stat.assent.component';


import { StatisticsAssentRoutingModule } from './stat.assent.routing.module';

import { StatisticsAssentService } from './stat.assent.service';



@NgModule({
  imports: [ CommonModule, FormsModule, StatisticsAssentRoutingModule,MaterialModule,MyDatePickerModule,FormsModule,ReactiveFormsModule],
  declarations: [StatisticsAssentComponent,ReportStatisticsAssentComponent],
  providers:    [ StatisticsAssentService]
})
export class StatisticsAssentModule { }