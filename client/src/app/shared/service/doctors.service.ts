import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {DoctorInformation} from "../model/DoctorInformation";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class DoctorsService {
  private baseUrl = "http://localhost:8080/api/doctors";

  constructor(private http: HttpClient) {}

  getDoctors(): Observable<DoctorInformation[]> {
    return this.http.get<DoctorInformation[]>(this.baseUrl)
      .pipe(catchError(this.handleError(undefined)));
  }

  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {
      return of(result as T);
    };
  }
}
