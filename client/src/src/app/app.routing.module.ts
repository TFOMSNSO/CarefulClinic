import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { HelloComponent } from './hello/hello.component';
import { CustomerComponent } from './customer/customer.component';
import { QuestionsComponent } from './questions/questions.component';



const routes: Routes = [
  { path: 'questions', component: QuestionsComponent },
  { path: 'hello', component: HelloComponent },
  { path: 'customer', component: CustomerComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}

