import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Department} from "../../../shared/model/Department";
import {catchError} from "rxjs/operators";
import {Room} from "../../../shared/model/Room";
import User, {UserLoginInput, UserSignupInput} from './user.model';
import {Observable, of} from 'rxjs';

import {User as UserDto} from '../../../shared/model/User';

@Injectable()
export class UserService {
  constructor(private http: HttpClient) {}
  private baseUrl = 'http://localhost:8080/api';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    withCredentials: true
  };

  private baseUrl2 = "http://localhost:8080/api/users";

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl2, this.httpOptions)
        .pipe(catchError(this.handleError(undefined)));
  }
  getCurrentUser(): Observable<UserDto> {
    return this.http.get<UserDto>(`${this.baseUrl}/currentUser`, this.httpOptions);
  }

  signup(input: UserSignupInput): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register`, JSON.stringify(input), this.httpOptions);
  }

  login(input: UserLoginInput): Observable<any> {
    const body = new URLSearchParams();
    body.append('email', input.username);
    body.append('password', input.password);

    const url = `${this.baseUrl}/login?${body}`;

    // this.httpOptions.headers.append('Authorization', 'Basic ' + btoa(input.username + ':' + input.password));
    // this.httpOptions.headers.append('Origin', 'htps://localhost:8080');
    // console.log(body.toString());

    return this.http.post<any>(url, body, this.httpOptions);
  }

  logout(): Observable<void> {
    const url = `${this.baseUrl}/logout`;
    return this.http.post<void>(url, '', this.httpOptions);
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
