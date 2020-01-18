import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {Appointment} from "../model/Appointment";
import {Department} from "../model/Department";

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
    providedIn: 'root'
})
export class AppointmentsService {

    private baseUrl = "http://localhost:8080/api/appointment/patient";

    constructor(private http: HttpClient) {
    }

    getMyAppointments(): Observable<Appointment[]> { //tre sa fie observable
        // const app1: Appointment = {
        //     id: 1,
        //     date: 'date',
        //     doctorId: 1,
        //     patientId: 1,
        //     room : 'room',
        //     status : 'approved'
        // };
        // let list: Appointment[] = [app1]
        //   return this.http.get<User>(`${this.baseUrl}/${id}`).pipe(catchError(this.handleError(undefined)));
        const id  =  2;
        return this.http.get<Appointment[]>(`${this.baseUrl}/${id}`)
            .pipe(catchError(this.handleError(undefined)));
        // return  of(list)
    }


    private handleError<T>(result?: T) {
        return (error: any): Observable<T> => {
            return of(result as T);
        };
    }
}
