import { NgModule } from '@angular/core';
import { HomeComponent } from './home.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SignupFormComponent } from './signup-form/signup-form.component';

@NgModule({
    imports: [NgbModule],
    declarations: [HomeComponent, SignupFormComponent],
    entryComponents: [HomeComponent, SignupFormComponent]
})
export class HomeModule {

}
