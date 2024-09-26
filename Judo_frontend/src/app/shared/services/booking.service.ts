import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ClientsType} from "../../../types/clients.type";
import {environment} from "../../../environments/environment";
import {BookingType} from "../../../types/booking.type";

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(private http: HttpClient) { }

  getBookings(): Observable<BookingType[]> {
    return this.http.get<BookingType[]>(environment.url + 'booking');
  }

  createBooking(userId: number, sessionId: number): Observable<BookingType> {
    return this.http.post<BookingType>(environment.url + 'booking', {
      userId, sessionId
    })
  }

}
