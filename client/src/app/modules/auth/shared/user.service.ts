import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import User, {UserLoginInput, UserSignupInput} from './user.model';
import {Observable, of} from 'rxjs';
import {Department} from "../../../shared/model/Department";
import {catchError} from "rxjs/operators";
import {Room} from "../../../shared/model/Room";

@Injectable()
export class UserService {
  constructor(private http: HttpClient) {}

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    withCredentials: true
  };

  getCurrentUser(): Observable<User> {
    return this.http.get<User>('/api/user', this.httpOptions);
  }
  private baseUrl = "http://localhost:8080/api/users";

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl)
        .pipe(catchError(this.handleError(undefined)));
  }

  signup(input: UserSignupInput): Observable<any> {
    const body = new URLSearchParams();

    body.append('fullName', input.fullName);
    body.append('email', input.email);
    body.append('password', input.password);

    return this.http.post<any>('/api/signup', body.toString(), this.httpOptions);
  }

  login(input: UserLoginInput): Observable<User> {
    const body = new URLSearchParams();
    body.append('email', input.username);
    body.append('password', input.password);
    return this.http.post<User>('/api/login', body.toString(), this.httpOptions);
  }

  logout(): Observable<void> {
    return this.http.post<void>('/api/logout', '', this.httpOptions);
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${id}`).pipe(catchError(this.handleError(undefined)));
  }
  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {
      return of(result as T);
    };
  }

}
