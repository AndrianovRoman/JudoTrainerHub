import { Component, OnInit } from '@angular/core';
import {SessionsType} from "../../../types/sessions.type";
import {AuthService} from "../../core/auth/auth.service";
import {SessionService} from "../../shared/services/session.service";
import {BookingService} from "../../shared/services/booking.service";

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.css']
})
export class SessionComponent implements OnInit {

  allSessions: SessionsType[] = [];
  sessionWithOutBooking: SessionsType[] = [];
  role: string | null = 'CLIENT';
  userId: number | null = null;

  constructor(private authService: AuthService,
              private sessionService: SessionService,
              private bookingService: BookingService) {
    this.role = this.authService.getIsRole();
    this.userId = +this.authService.getUserInfo().id!;
  }

  ngOnInit(): void {
    this.loadSessions();
  }

  loadSessions() {
    this.sessionService.getSessions()
      .subscribe(data => {
        this.allSessions = data;

        this.bookingService.getBookings()
          .subscribe(data => {
            // console.log(data);
            // console.log(this.allSessions);
            this.sessionWithOutBooking = this.allSessions.filter(item =>
              data.every(session => session.sessionId !== item.id));
            // console.log(this.sessionWithOutBooking);
          });

      })
  }



  //Удалить
  deleteSession(sessionId: number) {
    this.sessionService.deleteSession(sessionId)
      .subscribe(data => {
        console.log(data);
        this.loadSessions();
      })
  }

  //Записаться
  checkSession(sessionId: number) {
    this.bookingService.createBooking(this.userId!, sessionId)
      .subscribe(data => {
        console.log(data);
        this.loadSessions();
      })
  }

}
