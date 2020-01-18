import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import User, {UserLoginInput, UserSignupInput} from './user.model';
import {Observable, Subscription} from 'rxjs';

import {User as UserDto} from '../../../shared/model/User';
import {Router} from '@angular/router';

@Injectable()
export class UserService {
  constructor(private http: HttpClient) {}
  private baseUrl = 'http://localhost:8080/api';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  getCurrentUser(): Observable<UserDto> {
    return this.http.get<UserDto>(`${this.baseUrl}/currentUser`, this.httpOptions);
  }

  signup(input: UserSignupInput): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register`, JSON.stringify(input), this.httpOptions);
  }

  login(input: UserLoginInput): Subscription {
    const body = new URLSearchParams();
    body.append('email', input.username);
    body.append('password', input.password);

    const url = `${this.baseUrl}/login?${body}`;

    // this.httpOptions.headers.append('Authorization', 'Basic ' + btoa(input.username + ':' + input.password));
    // this.httpOptions.headers.append('Origin', 'htps://localhost:8080');
    // console.log(body.toString());

    return this.http.post<any>(url, body, this.httpOptions).subscribe(_ => this.router.navigateByUrl('/dashboard'));
  }

  logout(): Observable<void> {
    const url = `${this.baseUrl}/logout`;
    return this.http.post<void>(url, '', this.httpOptions);
  }

}
