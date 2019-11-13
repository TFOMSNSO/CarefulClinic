import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MyDatePickerModule } from 'mydatepicker';
import {MaterialModule} from './material-module';
import { RouterModule } from '@angular/router';
import { TableBasicExample } from './table-basic/table-basic-example';
import { HomeComponent } from './home/home.component';
import 'hammerjs';
import { Ng2CarouselamosModule } from 'ng2-carouselamos';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import {PeopleDatabase} from './list-prophylactic/people-database';
import { TableComponent } from './table/table.component';
import {TableHeaderDemo} from './table/table-header-demo';
import { ListProphylacticComponent } from './list-prophylactic/list-prophylactic.component';
import { DialogComponent } from './list-prophylactic/dialog.component';
import {ListProphylacticHeaderComponent} from './list-prophylactic/list-prophylactic.header.component';
import { SadeaveSearchComponent } from './list-prophylactic/sidenave.search.component';
import { SidenavExportExcelComponent } from './list-prophylactic/sidenav_export_excel/sidenav.export.excel.component';
import { SadeaveSearchKeysComponent } from './list-prophylactic/sidenav_search_keys/sidenave.search.keys.component';
import { SidenavUploadDataComponent } from './list-prophylactic/sidenav_uploaddata/sidenav.upload.data.component';
import { DndDirective } from './list-prophylactic/sidenav_uploaddata/dnd.directive';
import { AuthGuard } from './_guards/auth.guard';
import { LoginComponent } from './login/login.component';
import { AuthenticationService, UserService} from './_services/index';
import { ListZnoComponent } from './list-zno/list-zno.component';
import { SidenavSearchZnoComponent } from './list-zno/sidenav-search-zno/sidenav-search-zno.component';
import { HttpClientModule } from '@angular/common/http';
// used to create fake backend
import { fakeBackendProvider } from './_helpers/index';
import { MockBackend, MockConnection } from '@angular/http/testing';
import { BaseRequestOptions } from '@angular/http';
import { ListInformirovanieHeader} from './report/list-informirovanie/listinformirovanieheader.component';
import { TelephoneSurveysComponent} from './report/telephone-surveys/telephone-surveys.component';
	/*rout module*/
import { AppRoutingModule }     from './app.routing.module';
import { SidenaveSearchService } from './list-prophylactic/sidenave-search.service';
import { SidenavSearchZnoKeysComponent } from './list-zno/sidenav-search-zno-keys/sidenav-search-zno-keys.component';
import { SidenavUploaddataZnoComponent } from './list-zno/sidenav-uploaddata-zno/sidenav-uploaddata-zno.component';
import { SidenavExportExcelZnoComponent } from './list-zno/sidenav-export-excel-zno/sidenav-export-excel-zno.component';
import { DialogZnoComponent } from './list-zno/diaglog-zno/dialog-zno.component';
import {PeopleZnoDatabaseService} from "./list-zno/people-zno-database.service";
import { MoScheduleComponent } from './mo-schedule/table1/mo-schedule.component';
import {ScheduleHeaderComponent} from "./mo-schedule/table1/schedule.header.component";
import { DialogTable1Component } from './mo-schedule/table1/dialog-table1/dialog-table1.component';
import {MoSchedule4Component} from "./mo-schedule/table4/mo-schedule4.component";
import { MoSchedule2Component } from './mo-schedule/table2/mo-schedule2.component';
import { MoSchedule5Component } from './mo-schedule/table5/mo-schedule5/mo-schedule5.component';
import { MoSchedule3Component } from './mo-schedule/table3/mo-schedule3/mo-schedule3.component';
import { MoSchedule6Component } from './mo-schedule/table6/mo-schedule6/mo-schedule6.component';
import { Dialogtable3Component } from './mo-schedule/table3/dialogtable3/dialogtable3.component';
import { ScheduleHeader4Component } from './mo-schedule/table4/schedule-header4/schedule-header4.component';
import { HistoryDialogComponent } from './mo-schedule/table4/history-dialog/history-dialog.component';
import { Historydialog1Component } from './mo-schedule/table1/historydialog1/historydialog1.component';


//библиотека для ограничения доступа по ролям
//import { NgxPermissionsModule } from 'ngx-permissions';




@NgModule({
  declarations: [
	TableBasicExample,
    AppComponent,
  	HomeComponent,
    LoginComponent,
    HeaderComponent,
	  FooterComponent,
    TableComponent,
    TableHeaderDemo,
    ListProphylacticComponent,
    DialogComponent
    ,ListProphylacticHeaderComponent
    ,SadeaveSearchComponent
    ,SadeaveSearchKeysComponent
    ,SidenavExportExcelComponent
    ,SidenavUploadDataComponent
    ,DndDirective
    ,ListInformirovanieHeader
    ,ListZnoComponent
    ,SidenavSearchZnoComponent
    ,SidenavSearchZnoKeysComponent
    ,SidenavUploaddataZnoComponent
    ,SidenavExportExcelZnoComponent
    ,DialogZnoComponent
    ,ScheduleHeaderComponent
    ,DialogTable1Component
    ,Dialogtable3Component
    ,TelephoneSurveysComponent
    ,MoScheduleComponent
    ,MoSchedule4Component
    ,MoSchedule5Component
    ,MoSchedule3Component
    ,MoSchedule6Component
    ,ScheduleHeader4Component
    ,HistoryDialogComponent
    ,Historydialog1Component
    ,MoSchedule2Component
  ],
  imports: [
    HttpClientModule,
  	MyDatePickerModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    AppRoutingModule,
	MaterialModule,
	ReactiveFormsModule,
	Ng2CarouselamosModule,
    RouterModule.forRoot([
        {path: '',                    component: HomeComponent},])//,
    //NgxPermissionsModule.forRoot()
  ],
  providers: [PeopleDatabase,PeopleZnoDatabaseService, SidenaveSearchService,AuthGuard,AuthenticationService,
  // providers used to create fake backend
        fakeBackendProvider,
        MockBackend,
        BaseRequestOptions,
        UserService
		],
  entryComponents: [DialogZnoComponent,DialogComponent,DialogTable1Component
                    ,Dialogtable3Component,HistoryDialogComponent,Historydialog1Component],
  bootstrap: [AppComponent]
})
export class AppModule { }
