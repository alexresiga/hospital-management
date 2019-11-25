import { NgModule } from '@angular/core';
import { HomeComponent } from './home.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SignupFormComponent } from './signup-form/signup-form.component';
import {AuthModule} from '../auth/auth.module';

@NgModule({
  imports: [NgbModule, AuthModule],
    declarations: [HomeComponent, SignupFormComponent],
    entryComponents: [HomeComponent, SignupFormComponent]
})
export class HomeModule {

}
