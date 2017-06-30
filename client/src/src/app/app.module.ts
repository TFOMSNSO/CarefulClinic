import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { RouterModule }   from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MdButtonModule,MdCardModule,MdMenuModule,MdToolbarModule,MdIconModule } from '@angular/material';

import { AppComponent } from './app.component';
import { HelloComponent } from './hello/hello.component';
import { QuestionsComponent } from './questions/questions.component';

@NgModule({
  declarations: [
    AppComponent,
    HelloComponent,
    QuestionsComponent
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
    ReactiveFormsModule,
    RouterModule.forRoot([
      {
        path: 'questions',
        component: QuestionsComponent
      }
    ]),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
