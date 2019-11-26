import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SmartLoginComponent } from './login/smart-login/smart-login.component';
import {StoreModule} from '@ngrx/store';
import { reducer as UserReducer } from './shared/state/user.reducer';
import {ReactiveFormsModule} from '@angular/forms';
import {EffectsModule} from '@ngrx/effects';
import {UserEffects} from './shared/state/user.effects';
import {BrowserModule} from '@angular/platform-browser';
import {UserService} from './shared/user.service';
import {HttpClientModule} from '@angular/common/http';
import {LoginFormComponent} from './login/login-form/login-form.component';


@NgModule({
  declarations: [SmartLoginComponent, LoginFormComponent],
  exports: [
    SmartLoginComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    HttpClientModule,
    StoreModule.forRoot([UserReducer]),
    EffectsModule.forRoot([UserEffects]),
    ReactiveFormsModule
  ],
  providers: [
    UserService
  ]
})
export class AuthModule { }
