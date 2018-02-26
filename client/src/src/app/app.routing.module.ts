import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { TableComponent } from './table/table.component';
import { ListProphylacticComponent } from './list-prophylactic/list-prophylactic.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './_guards/auth.guard';
import { LoginComponent } from './login/login.component';
import {  ListInformirovanieHeader} from './list-informirovanie/listinformirovanieheader.component';

import { TableBasicExample } from './table-basic/table-basic-example'; 



const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'admin_cms', loadChildren: 'app/admin/admin.module#AdminModule', canActivate: [AuthGuard] },
  { path: 'sp3', loadChildren: 'app/report/sp3/sp3.module#Sp3Module', canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'table', component: TableComponent },
  { path: 'list_prophylactic', component: ListProphylacticComponent, canActivate: [AuthGuard]  },
  { path: 'list_header_inform', component: ListInformirovanieHeader, canActivate: [AuthGuard]  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}

