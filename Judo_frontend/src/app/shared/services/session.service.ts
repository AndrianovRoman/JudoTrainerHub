import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {SessionsType} from "../../../types/sessions.type";
import {UserInfoType} from "../../../types/user-info.type";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private http: HttpClient) { }

  getSessions(): Observable<SessionsType[]> {
    return this.http.get<SessionsType[]>(environment.url + 'session');
  }

  getOneSession(id: number): Observable<SessionsType> {
    return this.http.get<SessionsType>(environment.url + 'session/' + id);
  }

  updateSession(params: SessionsType, id: number): Observable<SessionsType> {
    return this.http.put<SessionsType>(environment.url + `session/` + id, params);
  }

  deleteSession(id: number): Observable<SessionsType> {
    return this.http.delete<SessionsType>(environment.url + 'session/' + id);
  }

  createSession(sessionDate: string, startTime: string, endTime: string, description: string): Observable<SessionsType> {
    return this.http.post<SessionsType>(environment.url + 'session', {
      sessionDate, startTime, endTime, description
    })
  }

}
