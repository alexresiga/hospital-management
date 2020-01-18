import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Prescription} from "../model/Prescription";
import {catchError} from "rxjs/operators";
import {User} from "../model/User";
import {Appointment} from "../model/Appointment";
import {Drug} from "../model/Drug";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'}),
  withCredentials: true
};

@Injectable({
  providedIn: 'root'
})
export class PrescriptionsService {
  private baseUrl = 'http://localhost:8080/api/prescription';
  private attachToAppointUrl = 'http://localhost:8080/api/appointment';
  private drugUrl = 'http://localhost:8080/api/drugList';
  private userUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  getPrescriptions(): Observable<Prescription[]> {
    return this.http.get<Prescription[]>(this.baseUrl, httpOptions).pipe(catchError(this.handleError(undefined)));
  }

  getAppointments(): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(this.attachToAppointUrl, httpOptions).pipe(catchError(this.handleError(undefined)));
  }

  getDrugs() : Observable<Drug[]> {
    return this.http.get<Drug[]>(this.drugUrl, httpOptions).pipe(catchError(this.handleError(undefined)));
  }

  getPatient(id: number) : Observable<User> {
    return this.http.get<User>(this.userUrl+"/"+id, httpOptions).pipe(catchError(this.handleError(undefined)));
  }

  addPrescription(appointID: number, prescription : Prescription):Observable<Prescription>{
    let url = this.attachToAppointUrl + "/" + appointID + "/prescription";
    return this.http.post(url,prescription,httpOptions).pipe(
        catchError(this.handleError(undefined)));
  }

  getPatients(): Observable<User[]>{
    return this.http.get<User[]>(this.userUrl, httpOptions).pipe(catchError(this.handleError(undefined)));
  }

  getPrescriptionsOfAPatient(patient: number | User) : Observable<Prescription[]> {
    let patientID = typeof patient === 'number' ? patient : patient.id;
    let url = this.baseUrl + "/patient/" + patientID;
    return this.http.get<Prescription[]>(url, httpOptions).pipe(catchError(this.handleError(undefined)));
  }

  getPrescriptionById(id:number): Observable<Prescription>{
    let url = this.baseUrl + "/" + id;
    return this.http.get<Prescription>(url, httpOptions).pipe(catchError(this.handleError(undefined)));
  }

  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {
      return of(result as T);
    };
  }
}
