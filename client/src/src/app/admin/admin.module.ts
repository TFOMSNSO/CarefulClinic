import { NgModule }            from '@angular/core';
import { CommonModule }        from '@angular/common';
import { FormsModule }         from '@angular/forms';
import {MaterialModule} from '../material-module';

import { AdminComponent } from './admin.component';
import { BreadcrumbComponent } from './breadcrumb/breadcrumb.component';
import { BrowserComponent } from './browser_component/browser.component';
import { ScIComponent } from './sci/sci.component';
import { AdminMenuInformComponent } from './admin_menu_inform/admin.menu.inform.component';

import { AdminRoutingModule } from './admin-routing.module';

import { AdminService } from './admin.service';
import { ShareService } from './share_service/share.service';


@NgModule({
  imports: [ CommonModule, FormsModule, AdminRoutingModule,MaterialModule],
  declarations: [AdminComponent,BreadcrumbComponent, AdminMenuInformComponent,ScIComponent,BrowserComponent],
  providers:    [ AdminService, ShareService ]
})
export class AdminModule { }