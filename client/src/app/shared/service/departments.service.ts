import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {Department} from '../model/Department';
import {Room} from '../model/Room';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class DepartmentsService {

  private baseUrl = 'http://localhost:8080/api/departments';

  constructor(private http: HttpClient) {
  }

  getDepartments(): Observable<Department[]> {
    return this.http.get<Department[]>(this.baseUrl)
        .pipe(catchError(this.handleError(undefined)));
  }

  add(dept: Department): Observable<Department> {
    return this.http.post(this.baseUrl, dept, httpOptions).pipe(
        catchError(this.handleError(undefined)));
  }

  delete(dept: number | Department): Observable<Department> {
    const id = typeof dept === 'number' ? dept : dept.id;
    const url = `${this.baseUrl}/${id}`;

    return this.http.delete<Department>(url, httpOptions).pipe(catchError(this.handleError(undefined)));
  }

  getDepartmentByID(id: number): Observable<Department> {
    return this.http.get<Department>(`${this.baseUrl}/${id}`).pipe(catchError(this.handleError(undefined)));
  }

  updateDepartment(dept: Department): Observable<Department> {
    const id = dept.id;
    const url = `${this.baseUrl}/${id}`;
    return  this.http.put<Department>(url, dept, httpOptions).pipe(catchError(this.handleError(undefined)));
  }


  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {
      return of(result as T);
    };
  }
}
