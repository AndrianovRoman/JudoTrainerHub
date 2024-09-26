import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../core/auth/auth.service";
import {UserService} from "../../../shared/services/user.service";
import {FormBuilder, Validators} from "@angular/forms";
import {UserInfoType} from "../../../../types/user-info.type";
import {TrainersService} from "../../../shared/services/trainers.service";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  userInfo: any;
  role: string | null = 'CLIENT';

  userInfoForm = this.fb.group({
    firstName: [''],
    lastName: [''],
    phoneNumber: [''],
    email: ['', Validators.required],
    qualificationId: [1],
  })

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private trainerService: TrainersService,
              private userService: UserService) {
    this.role = this.authService.getIsRole();
  }

  ngOnInit(): void {
    this.userInfo = this.authService.getUserInfo();
    this.userService.getOneUser(this.userInfo.id)
      .subscribe(data => {

        const user = data;

        const paramsToUpdate = {
          firstName: user.firstName ? user.firstName : '',
          lastName: user.lastName ? user.lastName : '',
          phoneNumber: user.phoneNumber ? user.phoneNumber : '',
          email: user.email ? user.email : '',
          qualificationId: user.qualificationId ? user.qualificationId : 1,
        }

        this.userInfoForm.setValue(paramsToUpdate);

      })
  }

  updateInfo() {
    if (this.role == 'TRAINER') {
      this.updateTrainerInfo();
    } else {
      this.updateUserInfo();
    }
  }

  updateUserInfo() {
    if (this.userInfoForm.valid) {

      const paramObject: UserInfoType = {
        email: this.userInfoForm.value.email ? this.userInfoForm.value.email : '',
      }

      if(this.userInfoForm.value.firstName) {
        paramObject.firstName = this.userInfoForm.value.firstName;
      }
      if(this.userInfoForm.value.lastName) {
        paramObject.lastName = this.userInfoForm.value.lastName;
      }
      if(this.userInfoForm.value.phoneNumber) {
        paramObject.phoneNumber = this.userInfoForm.value.phoneNumber;
      }
      // if(this.userInfoForm.value.qualificationId) {
      //   paramObject.qualificationId = this.userInfoForm.value.qualificationId;
      // }

      this.userService.updateUserInfo(paramObject, this.userInfo.id)
        .subscribe(data => {
          console.log(data);
          this.userInfoForm.markAsPristine();
        });
    }
  }

  updateTrainerInfo() {
    if (this.userInfoForm.valid) {

      const paramObject: UserInfoType = {
        email: this.userInfoForm.value.email ? this.userInfoForm.value.email : '',
      }

      if(this.userInfoForm.value.firstName) {
        paramObject.firstName = this.userInfoForm.value.firstName;
      }
      if(this.userInfoForm.value.lastName) {
        paramObject.lastName = this.userInfoForm.value.lastName;
      }
      if(this.userInfoForm.value.phoneNumber) {
        paramObject.phoneNumber = this.userInfoForm.value.phoneNumber;
      }
      if(this.userInfoForm.value.qualificationId) {
        paramObject.qualificationId = this.userInfoForm.value.qualificationId;
      }

      this.trainerService.updateTrainer(paramObject, this.userInfo.id)
        .subscribe(data => {
          console.log(data);
          this.userInfoForm.markAsPristine();
        });
    }
  }

  deleteUser() {
    this.userService.deleteUser(this.userInfo.id)
      .subscribe(data => {
        console.log(data);
        this.authService.logout();
      })
    console.log('delete account!');
  }

}
