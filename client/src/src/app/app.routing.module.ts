import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { TableComponent } from './table/table.component';
import { ListProphylacticComponent } from './list-prophylactic/list-prophylactic.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './_guards/auth.guard';
import { LoginComponent } from './login/login.component';
import { ListInformirovanieHeader} from './report/list-informirovanie/listinformirovanieheader.component';
import { TelephoneSurveysComponent} from './report/telephone-surveys/telephone-surveys.component';

import { TableBasicExample } from './table-basic/table-basic-example';
import {ListZnoComponent} from "./list-zno/list-zno.component";
import {MoScheduleComponent} from "./mo-schedule/table1/mo-schedule.component";
import {MoSchedule4Component} from "./mo-schedule/table4/mo-schedule4.component";
import {MoSchedule2Component} from "./mo-schedule/table2/mo-schedule2.component";
import {MoSchedule5Component} from "./mo-schedule/table5/mo-schedule5/mo-schedule5.component";
import {MoSchedule6Component} from "./mo-schedule/table6/mo-schedule6/mo-schedule6.component";
import {MoSchedule3Component} from "./mo-schedule/table3/mo-schedule3/mo-schedule3.component";



const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'admin_cms', loadChildren: 'app/admin/admin.module#AdminModule', canActivate: [AuthGuard] },
  { path: 'sp3', loadChildren: 'app/report/sp3/sp3.module#Sp3Module', canActivate: [AuthGuard] },
  { path: 'statistics-inform', loadChildren: 'app/report/statistic-informirovanie/stat.inform.module#StatisticsInformirovanieModule', canActivate: [AuthGuard] },
  { path: 'statistics-assent', loadChildren: 'app/report/statistic-assent/stat.assent.module#StatisticsAssentModule', canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'table', component: TableComponent },
  { path: 'list_prophylactic', component: ListProphylacticComponent, canActivate: [AuthGuard]  },
  { path: 'list_zno', component: ListZnoComponent, canActivate: [AuthGuard]},
  { path: 'list_header_inform', component: ListInformirovanieHeader, canActivate: [AuthGuard]  },
  { path: 'telephone-surveys', component: TelephoneSurveysComponent, canActivate: [AuthGuard]  },
  { path: 'mo_schedule', component: MoScheduleComponent, canActivate: [AuthGuard]  },
  { path: 'mo_schedule2', component: MoSchedule2Component, canActivate: [AuthGuard]  },
  { path: 'mo_schedule3', component: MoSchedule3Component, canActivate: [AuthGuard]  },
  { path: 'mo_schedule4', component: MoSchedule4Component, canActivate: [AuthGuard]  },
  { path: 'mo_schedule5', component: MoSchedule5Component, canActivate: [AuthGuard]  },
  { path: 'mo_schedule6', component: MoSchedule6Component, canActivate: [AuthGuard]  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}

