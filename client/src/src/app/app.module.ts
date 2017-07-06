import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MdButtonModule,
		 MaterialModule,
		 MdCardModule,
		 MdMenuModule,
		 MdToolbarModule,
		 MdIconModule,
		 MdInputModule,
		 MdProgressSpinnerModule,
		 MdNativeDateModule,
		 MdDatepickerModule,
		 MdSelectModule } from '@angular/material';

import 'hammerjs';
import { AppComponent } from './app.component';
import { HelloComponent } from './hello/hello.component';
import { CustomerComponent } from './customer/customer.component';
import { AddressComponent } from './customer/address.component';
import { QuestionsComponent } from './questions/questions.component';
import { HeaderComponent } from './header/header.component';

	/*rout module*/
import { AppRoutingModule }     from './app.routing.module';

@NgModule({
  declarations: [
    AppComponent,
    HelloComponent,
    QuestionsComponent,
    HeaderComponent,
    CustomerComponent,
    AddressComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    BrowserAnimationsModule,
    MdButtonModule,
    MdMenuModule,
    MdCardModule,
    AppRoutingModule,
    MdToolbarModule,
    MdIconModule,
    MdInputModule,
    MdNativeDateModule,
	MdDatepickerModule,
    MdSelectModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
