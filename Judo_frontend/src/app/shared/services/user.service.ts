import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ClientsType} from "../../../types/clients.type";
import {environment} from "../../../environments/environment";
import {UserInfoType} from "../../../types/user-info.type";
import * as http from "http";
import * as cluster from "cluster";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getOneUser(id: string): Observable<ClientsType> {
    return this.http.get<ClientsType>(environment.url + 'user/' + id);
  }

  updateUserInfo(params: UserInfoType, id: string): Observable<any> {
    return this.http.put<any>(environment.url + `user/` + id, params);
  }

  deleteUser(id: string): Observable<any> {
    return this.http.delete<any>(environment.url + `user/` + id);
  }
}
