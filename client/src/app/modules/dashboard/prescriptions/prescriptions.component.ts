import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Prescription} from "../../../shared/model/Prescription";
import {MatTableDataSource} from "@angular/material/table";
import {Appointment} from "../../../shared/model/Appointment";
import {PrescriptionsService} from "../../../shared/service/prescriptions.service";
import {Drug} from "../../../shared/model/Drug";
import {User} from "../../../shared/model/User";
import {UserService} from "../../auth/shared/user.service";

@Component({
  selector: 'app-prescriptions',
  templateUrl: './prescriptions.component.html',
  styleUrls: ['./prescriptions.component.css']
})
export class PrescriptionsComponent implements OnInit {
  prescriptions: Prescription[];
  appointments: Appointment[];
  drugs: Drug[];
  patients: User[];
  currentUser: User;
  selectedDrugs: Array<number> = new Array<number>();
  displayedColumns: string[] = ['doctor', 'patient', 'note', 'drugs'];
  dataSource: MatTableDataSource<Prescription>;


  constructor(private prescriptionService: PrescriptionsService, private userService: UserService) { }

  ngOnInit() {
    this.loadAppointmentsAndPrescriptionsAndDrugs();
  }

  getAppointments() : void{
      this.prescriptionService.getAppointments()
          .subscribe(appoints => {
            console.log(appoints);
            this.appointments = appoints;
          });
  }

  getDrug(id: number): string{
      for(let i=0;i<this.drugs.length;i++){
          if(this.drugs[i].id == id){
              return this.drugs[i].name;
          }
      }
  }

  getPrescriptions() : void{
      this.prescriptionService.getPrescriptions()
          .subscribe(prescriptions =>{
            console.log(prescriptions);
            this.prescriptions = prescriptions;
            console.log(this.currentUser);
            if (this.currentUser.role == 2) {
                this.prescriptions = this.prescriptions.filter(pres => {return pres.patient == this.currentUser.id});
            }
            console.log(this.prescriptions);
            this.dataSource = new MatTableDataSource<Prescription>(this.prescriptions);
          });
  }

  getDrugs() : void{
      this.prescriptionService.getDrugs()
          .subscribe(drugs => {
            console.log(drugs);
            this.drugs = drugs;
          });
  }

  addPrescription(appoint: number, note: string) : void{
      console.log(appoint);
      console.log(note);
      this.prescriptionService.addPrescription(appoint, {id:null, doctor:null, patient:null,note: note, drugs: this.selectedDrugs} as Prescription)
          .subscribe(pres=>{
              // this.selectedDrugs = new Array<number>();

              // this.ngOnInit();
              this.ngOnInit();
          });
  }

  getPatient(id: number) : string {
      for(let i=0;i<this.patients.length;i++){
          if (this.patients[i].id == id){
              return this.patients[i].full_name;
          }
      }
  }

  change(event) : void{
      if(event.isUserInput) {
          if(event.source.selected)
          { this.selectedDrugs.push(event.source.value);
          }
          else
          { this.selectedDrugs = this.selectedDrugs.filter(drug => drug != event.source.value);}
      }
  }

  getPatients() : void {
      this.prescriptionService.getPatients().subscribe(patients => {
          console.log(patients);
          this.patients = patients;
      });
  }

    getCurrentUser(): void {
        this.userService.getCurrentUser().subscribe(user => this.currentUser = user);
    }

  loadAppointmentsAndPrescriptionsAndDrugs() : void{
    this.getCurrentUser();
    this.getDrugs();
    this.getPatients();
    this.getAppointments();
    this.getPrescriptions();

    this.dataSource._renderChangesSubscription;
    this.dataSource._updateChangeSubscription();


  }



}
