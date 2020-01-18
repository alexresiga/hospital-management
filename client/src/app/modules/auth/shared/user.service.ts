import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import User, {UserLoginInput, UserSignupInput} from './user.model';
import {Observable} from 'rxjs';

@Injectable()
export class UserService {
  constructor(private http: HttpClient) {}
  private baseUrl = 'http://localhost:8080/api';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getCurrentUser(): Observable<User> {
    return this.http.get<User>('/api/user', this.httpOptions);
  }

  signup(input: UserSignupInput): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register`, JSON.stringify(input), this.httpOptions);
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

}
