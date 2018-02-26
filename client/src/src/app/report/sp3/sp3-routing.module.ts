import { NgModule }     from '@angular/core';
import { Routes, RouterModule } from '@angular/router';



import { Sp3Component } from './sp3.component';
import { ExpertiseComponent } from './expertise/expertise.component';



const routes: Routes = [
  { path: '', component: Sp3Component,
	children: [
      { path: 'expertise', component: ExpertiseComponent }
	 // { path: 'content/report/informing', component: BrowserComponent}*/
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Sp3RoutingModule {}