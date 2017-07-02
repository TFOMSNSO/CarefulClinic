import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule }   from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MdButtonModule,
		 MdCardModule,
		 MdMenuModule,
		 MdToolbarModule,
		 MdIconModule,
		 MdSelectModule } from '@angular/material';

import 'hammerjs';
import { AppComponent } from './app.component';
import { HelloComponent } from './hello/hello.component';
import { CustomerComponent } from './customer/customer.component';
import { AddressComponent } from './customer/address.component';
import { QuestionsComponent } from './questions/questions.component';

@NgModule({
  declarations: [
    AppComponent,
    HelloComponent,
    QuestionsComponent,
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
    MdToolbarModule,
    MdIconModule,
    MdSelectModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      {
        path: 'questions',
        component: QuestionsComponent
      },
      {
        path: 'hello',
        component: HelloComponent
      },
      {
        path: 'customer',
        component: CustomerComponent
      }
    ]),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
