import { NgModule } from '@angular/core';
import { DashboardComponent } from './dashboard.component';
import { NavbarComponent } from './navbar/navbar.component';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DepartmentsAndRoomsComponent } from './departments-and-rooms/departments-and-rooms.component';
import { DrugsComponent } from './drugs/drugs.component';
import { DoctorsComponent } from './doctors/doctors.component';
import {RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatIconModule} from '@angular/material/icon';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import {MatTableModule} from '@angular/material/table';
import {MatTabsModule} from '@angular/material/tabs';
import {PrescriptionsComponent} from "./prescriptions/prescriptions.component";
import {AppointmentsComponent} from "./appointments/appointments.component";


@NgModule({
  imports: [
    CommonModule,
    NgbModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatGridListModule,
    MatIconModule,
    MatSelectModule,
    MatInputModule,
    MatButtonModule,
    MatTableModule,
    MatTabsModule
  ],
<<<<<<< HEAD

  declarations: [DashboardComponent, NavbarComponent, DepartmentsAndRoomsComponent, DoctorsComponent, PrescriptionsComponent, AppointmentsComponent],
=======
  declarations: [DashboardComponent, NavbarComponent, DepartmentsAndRoomsComponent, DrugsComponent, DoctorsComponent, PrescriptionsComponent],
>>>>>>> 7f635aa58551e65329df5b83173adc2e27d47ac3
  exports: []
})
export class DashboardModule {}
