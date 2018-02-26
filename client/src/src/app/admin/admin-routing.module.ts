import { NgModule }     from '@angular/core';
import { Routes, RouterModule } from '@angular/router';



import { AdminComponent } from './admin.component';
import { BrowserComponent } from './browser_component/browser.component';
import { AdminMenuInformComponent } from './admin_menu_inform/admin.menu.inform.component';
import { ScIComponent } from './sci/sci.component';

const routes: Routes = [
  { path: '', component: AdminComponent,
	children: [
      { path: 'sci', component: ScIComponent },
	  { path: 'content/report/informing', component: BrowserComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}