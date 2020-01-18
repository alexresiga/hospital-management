import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {HomeModule} from './modules/home/home.module';
import { StoreModule } from '@ngrx/store';
import { reducers, metaReducers } from './shared/store';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { environment } from '../environments/environment';
import { DashboardModule } from './modules/dashboard/dashboard.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AppRoutingModule} from "./app-routing.module";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HomeModule,
    DashboardModule,
    StoreModule.forRoot(reducers, {
      metaReducers,
      runtimeChecks: {
        strictStateImmutability: true,
        strictActionImmutability: true,
      }
    }),
    !environment.production ? StoreDevtoolsModule.instrument() : [],
    BrowserAnimationsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
