import { Injectable } from '@angular/core';
import {Observable, Subject, throwError} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LoginResponseType} from "../../../types/login-response.type";
import {environment} from "../../../environments/environment";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public tokenKey: string = 'token';
  // public roleKey: string = 'role';
  public idKey: string = 'id';

  public isLogged$: Subject<boolean> = new Subject<boolean>();
  private isLogged: boolean = false;

  // public isRole$: Subject<string> = new Subject<string>();
  private isRole: string | null = 'CLIENT';

  constructor(private http: HttpClient,
              private router: Router){
    this.isLogged = !!localStorage.getItem(this.tokenKey);
    // this.isRole = localStorage.getItem(this.roleKey);
  }

  login(login: string, password: string): Observable<LoginResponseType> {
    return this.http.post<LoginResponseType>(environment.url + 'user/login', {
      login, password
    })
  }

  signup(firstName: string, lastName: string, phoneNumber: string, email: string, password: string ): Observable<any> {
    return this.http.post<any>(environment.url + 'user/signup', {
      firstName, lastName, phoneNumber, email, password
    })
  }

  public getIsLoggedIn() {
    return this.isLogged;
  }

  public getIsRole() {
    return this.isRole;
  }

  public setTokens(token: string, role: string, id: string): void {
    localStorage.setItem(this.tokenKey, token);
    // localStorage.setItem(this.roleKey, role);
    localStorage.setItem(this.idKey, id);
    this.isRole = role;
    this.isLogged = true;
    this.isLogged$.next(true);
  }

  public removeTokens(): void {
    localStorage.removeItem(this.tokenKey);
    // localStorage.removeItem(this.roleKey);
    localStorage.removeItem(this.idKey);
    this.isLogged = false;
    this.isLogged$.next(false);
  }

  public getTokens(): {token: string | null} {
    return {
      token: localStorage.getItem(this.tokenKey)
    }
  }

  public getUserInfo(): {role: string | null, id: string | null} {
    return {
      // role: localStorage.getItem(this.roleKey),
      role: this.isRole,
      id: localStorage.getItem(this.idKey)
    }
  }

  logout(): void {
    this.removeTokens();
    this.isRole = 'CLIENT';
    this.router.navigate(['/']);
  }

}
