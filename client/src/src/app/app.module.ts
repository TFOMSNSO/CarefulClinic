import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MyDatePickerModule } from 'mydatepicker';
import {MaterialModule} from './material-module';


import { TableBasicExample } from './table-basic/table-basic-example'; 

import { HomeComponent } from './home/home.component';

//import {CdkTableModule} from '@angular/cdk';
import 'hammerjs';
import { Ng2CarouselamosModule } from 'ng2-carouselamos';
import { AppComponent } from './app.component';
//import { HelloComponent } from './hello/hello.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
//import {PeopleDatabase} from './table/people-database';
import {PeopleDatabase} from './list-prophylactic/people-database';
import { TableComponent } from './table/table.component';
import {TableHeaderDemo} from './table/table-header-demo';
import { ListProphylacticComponent } from './list-prophylactic/list-prophylactic.component';
import { DialogComponent } from './list-prophylactic/dialog.component';
//import { DialogDemo } from './z_dialog/dialog-demo';
//import { JazzDialog } from './z_dialog/dialog2-demo';
//import { ContentElementDialog } from './z_dialog/dialog3-demo';
//import { IFrameDialog } from './z_dialog/dialog4-demo';
import {ListProphylacticHeaderComponent} from './list-prophylactic/list-prophylactic.header.component';
import { SadeaveSearchComponent } from './list-prophylactic/sidenave.search.component';
import { SidenavExportExcelComponent } from './list-prophylactic/sidenav_export_excel/sidenav.export.excel.component';
import { SadeaveSearchKeysComponent } from './list-prophylactic/sidenav_search_keys/sidenave.search.keys.component';
import { SidenavUploadDataComponent } from './list-prophylactic/sidenav_uploaddata/sidenav.upload.data.component';
import { DndDirective } from './list-prophylactic/sidenav_uploaddata/dnd.directive'; 
import { AuthGuard } from './_guards/auth.guard';
import { LoginComponent } from './login/login.component';
import { AuthenticationService, UserService} from './_services/index';
// used to create fake backend
import { fakeBackendProvider } from './_helpers/index';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { BaseRequestOptions } from '@angular/http';
import { ListInformirovanieHeader} from './report/list-informirovanie/listinformirovanieheader.component';







	/*rout module*/
import { AppRoutingModule }     from './app.routing.module';
import { SidenaveSearchService } from './list-prophylactic/sidenave-search.service';





@NgModule({
  declarations: [
	TableBasicExample,
    AppComponent,
  	HomeComponent,
    LoginComponent,
//    HelloComponent,
    HeaderComponent,
	FooterComponent,
    TableComponent,
    TableHeaderDemo,
    ListProphylacticComponent,
    DialogComponent
	//DialogDemo
    //,JazzDialog
    //,IFrameDialog
    ,ListProphylacticHeaderComponent
    ,SadeaveSearchComponent
    ,SadeaveSearchKeysComponent
    ,SidenavExportExcelComponent
    ,SidenavUploadDataComponent
    ,DndDirective
    ,ListInformirovanieHeader
	
  ],
  imports: [
  	MyDatePickerModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    AppRoutingModule,
	MaterialModule,
	ReactiveFormsModule,
	Ng2CarouselamosModule
  ],
  providers: [PeopleDatabase, SidenaveSearchService,AuthGuard,AuthenticationService,
  // providers used to create fake backend
        fakeBackendProvider,
        MockBackend,
        BaseRequestOptions,
        UserService
		],
  entryComponents: [DialogComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
