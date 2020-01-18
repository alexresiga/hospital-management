import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './modules/home/home.component';
import { DashboardComponent } from './modules/dashboard/dashboard.component';
import {DepartmentsAndRoomsComponent} from "./modules/dashboard/departments-and-rooms/departments-and-rooms.component";
import {DoctorsComponent} from "./modules/dashboard/doctors/doctors.component";
import {PrescriptionsComponent} from "./modules/dashboard/prescriptions/prescriptions.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'dashboard', component: DashboardComponent },
  {path: 'departments-and-rooms', component: DepartmentsAndRoomsComponent},
  {path: 'doctors', component: DoctorsComponent},
  {path: 'prescriptions', component: PrescriptionsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }