import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


import { HelloComponent } from './hello/hello.component';
import { QuestionsComponent } from './questions/questions.component';
import { TableComponent } from './table/table.component';
import { ListProphylacticComponent } from './list-prophylactic/list-prophylactic.component';
import { DialogDemo } from './z_dialog/dialog-demo';



const routes: Routes = [
  { path: 'questions', component: QuestionsComponent },
  { path: 'hello', component: HelloComponent },
  { path: 'table', component: TableComponent },
  { path: 'list_prophylactic', component: ListProphylacticComponent },
  { path: 'z_dialog', component: DialogDemo }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}

