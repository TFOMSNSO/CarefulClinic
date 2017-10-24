import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MyDatePickerModule } from 'mydatepicker';

import {
  MdButtonModule,
  MdButtonToggleModule,
  MdCardModule,
  MdCheckboxModule,
  MdChipsModule,
  MdCoreModule,
  MdTableModule,
  MdDatepickerModule,
  MdDialogModule,
  MdExpansionModule,
  MdGridListModule,
  MdIconModule,
  MdInputModule,
  MdListModule,
  MdMenuModule,
  MdNativeDateModule,
  MdPaginatorModule,
  MdProgressBarModule,
  MdProgressSpinnerModule,
  MdRadioModule,
  MdRippleModule,
  MdSelectModule,
  MdSidenavModule,
  MdSliderModule,
  MdSlideToggleModule,
  MdSnackBarModule,
  MdSortModule,
  MdTabsModule,
  MdToolbarModule,
  MdTooltipModule,
} from '@angular/material';


import { HomeComponent } from './home/home.component';
import {CdkTableModule} from '@angular/cdk';
import 'hammerjs';
import { AppComponent } from './app.component';
import { HelloComponent } from './hello/hello.component';
import { QuestionsComponent } from './questions/questions.component';
import { HeaderComponent } from './header/header.component';
//import {PeopleDatabase} from './table/people-database';
import {PeopleDatabase} from './list-prophylactic/people-database';
import { TableComponent } from './table/table.component';
import {TableHeaderDemo} from './table/table-header-demo';
import { ListProphylacticComponent } from './list-prophylactic/list-prophylactic.component';
import { DialogComponent } from './list-prophylactic/dialog.component';
import { DialogDemo } from './z_dialog/dialog-demo';
import { JazzDialog } from './z_dialog/dialog2-demo';
import { ContentElementDialog } from './z_dialog/dialog3-demo';
import { IFrameDialog } from './z_dialog/dialog4-demo';
import {ListProphylacticHeaderComponent} from './list-prophylactic/list-prophylactic.header.component';
import { SadeaveSearchComponent } from './list-prophylactic/sidenave.search.component';
import { SidenavExportExcelComponent } from './list-prophylactic/sidenav_export_excel/sidenav.export.excel.component';
import { SadeaveSearchKeysComponent } from './list-prophylactic/sidenav_search_keys/sidenave.search.keys.component';
import { AuthGuard } from './_guards/auth.guard';
import { LoginComponent } from './login/login.component';
import { AuthenticationService, UserService} from './_services/index';
// used to create fake backend
import { fakeBackendProvider } from './_helpers/index';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { BaseRequestOptions } from '@angular/http';







	/*rout module*/
import { AppRoutingModule }     from './app.routing.module';
import { SidenaveSearchService } from './list-prophylactic/sidenave-search.service';




@NgModule({
  declarations: [
    AppComponent,
  	HomeComponent,
    LoginComponent,
    HelloComponent,
    QuestionsComponent,
    HeaderComponent,
    TableComponent,
    TableHeaderDemo,
    ListProphylacticComponent,
    DialogComponent,DialogDemo
    ,JazzDialog
    ,ContentElementDialog
    ,IFrameDialog
    ,ListProphylacticHeaderComponent
    ,SadeaveSearchComponent
    ,SadeaveSearchKeysComponent
    ,SidenavExportExcelComponent
    
  ],
  imports: [
  	MyDatePickerModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    AppRoutingModule,
	MdButtonModule,
  MdButtonToggleModule,
  MdCardModule,
  MdCheckboxModule,
  MdChipsModule,
  MdCoreModule,
  MdTableModule,
  MdDatepickerModule,
  MdDialogModule,
  MdExpansionModule,
  MdGridListModule,
  MdIconModule,
  MdInputModule,
  MdListModule,
  MdMenuModule,
  MdNativeDateModule,
  MdPaginatorModule,
  MdProgressBarModule,
  MdProgressSpinnerModule,
  MdRadioModule,
  MdRippleModule,
  MdSelectModule,
  MdSidenavModule,
  MdSliderModule,
  MdSlideToggleModule,
  MdSnackBarModule,
  MdSortModule,
  MdTabsModule,
  MdToolbarModule,
  MdTooltipModule,
  CdkTableModule,
  ReactiveFormsModule
  ],
  providers: [PeopleDatabase, SidenaveSearchService,AuthGuard,AuthenticationService,
  // providers used to create fake backend
        fakeBackendProvider,
        MockBackend,
        BaseRequestOptions,
        UserService],
  entryComponents: [DialogComponent,JazzDialog,ContentElementDialog,IFrameDialog],
  bootstrap: [AppComponent]
})
export class AppModule { }
