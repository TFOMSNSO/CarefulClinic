import { NgModule }            from '@angular/core';
import { CommonModule }        from '@angular/common';
import {MaterialModule} from './material-module';
import { MyDatePickerModule } from 'mydatepicker';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

/* Компоненты родители */
import { StatisticsInformirovanieComponent } from './stat.inform.component';

/* Дочерние компоненты */
import { ReportStatisticsInformirovanieComponent } from './reports/report.stat.inform.component';


import { StatisticsInformirovanieRoutingModule } from './stat.inform.routing.module';

import { StatisticsInformirovanieService } from './stat.inform.service';



@NgModule({
  imports: [ CommonModule, FormsModule, StatisticsInformirovanieRoutingModule,MaterialModule,MyDatePickerModule,FormsModule,ReactiveFormsModule],
  declarations: [StatisticsInformirovanieComponent,ReportStatisticsInformirovanieComponent],
  providers:    [ StatisticsInformirovanieService]
})
export class StatisticsInformirovanieModule { }