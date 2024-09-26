import { Component, OnInit } from '@angular/core';
import {ClientsService} from "../../shared/services/clients.service";
import {ClientsType} from "../../../types/clients.type";
import {AuthService} from "../../core/auth/auth.service";
import {UserService} from "../../shared/services/user.service";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(private userService: UserService,
              private authService: AuthService) { }

  ngOnInit(): void {
    // const userInfo = this.authService.getUserInfo();
    // console.log(userInfo);
    //
    // if (userInfo.id) {
    //   this.userService.getOneUser(userInfo.id)
    //     .subscribe(data => {
    //       console.log(data);
    //     })
    // }
  }

}
