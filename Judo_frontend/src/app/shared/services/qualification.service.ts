import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {ClientsType} from "../../../types/clients.type";
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class QualificationService {

  constructor(private http: HttpClient) { }

  getQualifications(): Observable<any[]> {
    return this.http.get<any[]>(environment.url + 'qualification');
  }

}
