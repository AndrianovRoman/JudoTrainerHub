import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {ClientsType} from "../../../types/clients.type";

@Injectable({
  providedIn: 'root'
})
export class ClientsService {

  constructor(private http: HttpClient) { }

  getClients(): Observable<ClientsType[]> {
    return this.http.get<ClientsType[]>(environment.url + 'user/client');
  }
}
