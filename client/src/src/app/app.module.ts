import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
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

import {CdkTableModule} from '@angular/cdk';
import 'hammerjs';
import { AppComponent } from './app.component';
import { HelloComponent } from './hello/hello.component';
import { CustomerComponent } from './customer/customer.component';
import { AddressComponent } from './customer/address.component';
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







	/*rout module*/
import { AppRoutingModule }     from './app.routing.module';


@NgModule({
  declarations: [
    AppComponent,
    HelloComponent,
    QuestionsComponent,
    HeaderComponent,
    CustomerComponent,
    AddressComponent,
    TableComponent,
    TableHeaderDemo,
    ListProphylacticComponent,
    DialogComponent,DialogDemo
    ,JazzDialog
    ,ContentElementDialog
    ,IFrameDialog
    ,ListProphylacticHeaderComponent
    ,SadeaveSearchComponent
    
  ],
  imports: [
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
  providers: [PeopleDatabase],
  entryComponents: [DialogComponent,JazzDialog,ContentElementDialog,IFrameDialog],
  bootstrap: [AppComponent]
})
export class AppModule { }
