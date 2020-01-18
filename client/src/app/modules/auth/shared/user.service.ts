import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import User, {UserLoginInput, UserSignupInput} from './user.model';
import {Observable, Subscription} from 'rxjs';

import {User as UserDto} from "../../../shared/model/User";
import {Router} from "@angular/router";

@Injectable()
export class UserService {
  constructor(private http: HttpClient, private router: Router) {}

  private baseUrl = "http://localhost:8080";

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    }),
    withCredentials: true
  };
  getCurrentUser(): Observable<UserDto> {
    return this.http.get<UserDto>(`${this.baseUrl}/api/currentUser`, this.httpOptions);
  }

  signup(input: UserSignupInput): Observable<any> {
    const body = new URLSearchParams();

    body.append('fullName', input.fullName);
    body.append('email', input.email);
    body.append('password', input.password);

    return this.http.post<any>('/api/signup', body.toString(), this.httpOptions);
  }

  login(input: UserLoginInput): Subscription {
    const body = new URLSearchParams();
    body.append('email', input.username);
    body.append('password', input.password);

    const url = `${this.baseUrl}/api/login?${body}`;

    // this.httpOptions.headers.append('Authorization', 'Basic ' + btoa(input.username + ':' + input.password));
    // this.httpOptions.headers.append('Origin', 'htps://localhost:8080');
    // console.log(body.toString());

    return this.http.post<any>(url, body, this.httpOptions).subscribe(_ => this.router.navigateByUrl('/dashboard'));
  }

  logout(): Observable<void> {
    const url = `${this.baseUrl}/api/logout`;
    return this.http.post<void>(url, '', this.httpOptions);
  }

}
