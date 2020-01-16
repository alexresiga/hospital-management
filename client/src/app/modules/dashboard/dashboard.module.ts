import { NgModule } from '@angular/core';
import { DashboardComponent } from './dashboard.component';
import { NavbarComponent } from './navbar/navbar.component';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  imports: [
    CommonModule,
    NgbModule
  ],
  declarations: [DashboardComponent, NavbarComponent],
  exports: []
})
export class DashboardModule {}
