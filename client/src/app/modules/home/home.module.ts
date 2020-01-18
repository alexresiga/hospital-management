import { NgModule } from '@angular/core';
import { HomeComponent } from './home.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SignupFormComponent } from './signup-form/signup-form.component';
import {AuthModule} from '../auth/auth.module';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { UserEffects } from '../auth/shared/state/user.effects';
import { reducer as UserReducer } from '../auth/shared/state/user.reducer';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatSnackBarModule} from '@angular/material/snack-bar';

@NgModule({
  imports: [
    NgbModule,
    AuthModule,
    ReactiveFormsModule,
    CommonModule,
    HttpClientModule,
    StoreModule.forRoot([UserReducer]),
    EffectsModule.forRoot([UserEffects]),
    MatProgressSpinnerModule,
    MatSnackBarModule
  ],
  declarations: [HomeComponent, SignupFormComponent],
  entryComponents: [HomeComponent, SignupFormComponent]
})
export class HomeModule {

}
