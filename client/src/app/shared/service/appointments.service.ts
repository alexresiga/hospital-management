import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {Appointment} from "../model/Appointment";
import {Department} from "../model/Department";
import User from "../../modules/auth/shared/user.model";

const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'}),
    withCredentials:true
};

@Injectable({
    providedIn: 'root'
})
export class AppointmentsService {

    private baseUrl = "http://localhost:8080/api/appointment";

    constructor(private http: HttpClient) {
    }

    getMyAppointments(user:User): Observable<Appointment[]> { //tre sa fie observable
        if(user.role == 1){
            console.log("am ajuns aici");
            console.log(user);
            return this.http.get<Appointment[]>(`${this.baseUrl}/doctor/${user.id}`, httpOptions)
                .pipe(catchError(this.handleError(undefined)));
        }
        else {
            return this.http.get<Appointment[]>(`${this.baseUrl}/patient/${user.id}`, httpOptions)
                .pipe(catchError(this.handleError(undefined)));
        }
    }

    changeStatus(appointment:Appointment){
        var newStatus :String;
        if(appointment.approved=='approved'){
            newStatus = 'not_approved';
        }
        else{
            newStatus='approved';
        }
        console.log(newStatus);
        return this.http.post(`${this.baseUrl}/${appointment.id}/${newStatus}`, appointment,httpOptions).pipe(
            catchError(this.handleError(undefined)));
    }


    private handleError<T>(result?: T) {
        return (error: any): Observable<T> => {
            return of(result as T);
        };
    }
}
