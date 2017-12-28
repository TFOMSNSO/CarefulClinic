import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { HelloComponent } from './hello/hello.component';
import { QuestionsComponent } from './questions/questions.component';
import { TableComponent } from './table/table.component';
import { ListProphylacticComponent } from './list-prophylactic/list-prophylactic.component';
import { DialogDemo } from './z_dialog/dialog-demo';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './_guards/auth.guard';
import { LoginComponent } from './login/login.component';
import {  ListInformirovanieHeader} from './list-informirovanie/listinformirovanieheader.component';



const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'questions', component: QuestionsComponent },
  { path: 'hello', component: HelloComponent },
  { path: 'table', component: TableComponent },
  { path: 'list_prophylactic', component: ListProphylacticComponent, canActivate: [AuthGuard]  },
  { path: 'list_header_inform', component: ListInformirovanieHeader, canActivate: [AuthGuard]  },
  { path: 'z_dialog', component: DialogDemo },
   { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}

