import { NgModule }     from '@angular/core';
import { Routes, RouterModule } from '@angular/router';



import { Sp3Component } from './sp3.component';
import { ExpertiseComponent } from './expertise/expertise.component';
import { InfoDReestrComponent } from './info-d-reestr/info.d.reestr.component';



const routes: Routes = [
  { path: '', component: Sp3Component,
	children: [
      { path: 'expertise', component: ExpertiseComponent },
	  { path: 'info-d-reestr', component: InfoDReestrComponent }
	 
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Sp3RoutingModule {}