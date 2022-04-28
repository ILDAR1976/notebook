import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpClientModule }   from '@angular/common/http';
import { AppComponent }   from './app.component';
//import { APP_BASE_HREF } from '@angular/common';

@NgModule({
    imports:      [ BrowserModule, FormsModule, HttpClientModule],
    declarations: [ AppComponent ],
    //providers: [ {provide: APP_BASE_HREF, useValue: '/base'} ],
    bootstrap:    [ AppComponent ]
})
export class AppModule { }