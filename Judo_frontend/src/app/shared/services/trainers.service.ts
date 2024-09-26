import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {ClientsType} from "../../../types/clients.type";
import {UserInfoType} from "../../../types/user-info.type";

@Injectable({
  providedIn: 'root'
})
export class TrainersService {

  constructor(private http: HttpClient) { }

  getTrainers(): Observable<ClientsType[]> {
    return this.http.get<ClientsType[]>(environment.url + 'user/trainer');
  }

  createTrainer(firstName: string, lastName: string, phoneNumber: string, email: string, password: string, qualificationId: string): Observable<any> {
    return this.http.post<any>(environment.url + 'user/trainer', {
      firstName, lastName, phoneNumber, email, password, qualificationId
    })
  }

  updateTrainer(params: UserInfoType, id: string): Observable<any> {
    return this.http.put<any>(environment.url + 'user/trainer/' + id, params)
  }

}
