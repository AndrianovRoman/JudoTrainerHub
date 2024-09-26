import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../../core/auth/auth.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLogged: boolean = false;
  userInfo: any;

  constructor(private authService: AuthService) {
    this.isLogged = this.authService.getIsLoggedIn();
  }

  ngOnInit(): void {
    this.authService.isLogged$.subscribe((isLoggedIn: boolean) => {
      this.isLogged = isLoggedIn;
      // this.userInfo = this.authService.getUserInfo();
      // console.log(this.userInfo);
    });

  }

  logout(): void {
    this.authService.logout();
  }

}
