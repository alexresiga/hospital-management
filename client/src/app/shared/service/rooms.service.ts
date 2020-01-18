import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {Observable, of} from 'rxjs';
import {Room} from '../model/Room';


const httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
};


@Injectable({
    providedIn: 'root'
})
export class RoomsService {

    private baseUrl = 'http://localhost:8080/api/rooms';


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
        const id = typeof room === 'number' ? room : room.id;

        const url = `${this.baseUrl}/${id}`;

        return this.http.delete<Room>(url, httpOptions).pipe(catchError(this.handleError(undefined)));
    }

    getRoomByID(id: number): Observable<Room> {
        return this.http.get<Room>(`${this.baseUrl}/${id}`).pipe(catchError(this.handleError(undefined)));
    }

    updateRoom(room: Room): Observable<Room> {
        const id = room.id;
        const url = `${this.baseUrl}/${id}`;
        return this.http.put<Room>(url, room, httpOptions).pipe(catchError(this.handleError(undefined)));
    }


    private handleError<T>(result?: T) {
        return (error: any): Observable<T> => {
            return of(result as T);
        };
    }
}
