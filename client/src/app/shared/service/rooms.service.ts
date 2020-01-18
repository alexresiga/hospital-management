import { Injectable } from '@angular/core';
<<<<<<< HEAD
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {Observable, of} from 'rxjs';
import {Room} from '../model/Room';
=======
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {Observable, of} from "rxjs";
import {Room} from "../model/Room";
>>>>>>> fb9702694ec9fd42790d45f284dc79ea47ac80b0


const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};


@Injectable({
  providedIn: 'root'
})
export class RoomsService {

<<<<<<< HEAD
  private baseUrl = 'http://localhost:8080/api/rooms';
=======
  private baseUrl = "http://localhost:8080/api/rooms";
>>>>>>> fb9702694ec9fd42790d45f284dc79ea47ac80b0

  constructor(private http: HttpClient) {
  }

  getRooms(): Observable<Room[]> {
    return this.http.get<Room[]>(this.baseUrl)
        .pipe(catchError(this.handleError(undefined)));
  }

  addRoom(room: Room): Observable<Room> {
    return this.http.post(this.baseUrl, room, httpOptions).pipe(
        catchError(this.handleError(undefined)));
  }

  deleteRoom(room: number | Room): Observable<Room> {
<<<<<<< HEAD
    const id = typeof room === 'number' ? room : room.id;
=======
    const id = typeof room === "number" ? room : room.id;
>>>>>>> fb9702694ec9fd42790d45f284dc79ea47ac80b0
    const url = `${this.baseUrl}/${id}`;

    return this.http.delete<Room>(url, httpOptions).pipe(catchError(this.handleError(undefined)));
  }

  getRoomByID(id: number): Observable<Room> {
    return this.http.get<Room>(`${this.baseUrl}/${id}`).pipe(catchError(this.handleError(undefined)));
  }

  updateRoom(room: Room): Observable<Room> {
    const id = room.id;
    const url = `${this.baseUrl}/${id}`;
    return  this.http.put<Room>(url, room, httpOptions).pipe(catchError(this.handleError(undefined)));
  }


  private handleError<T>(result?: T) {
    return (error: any): Observable<T> => {
      return of(result as T);
    };
  }
}
