import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {AuthService} from "../../../core/auth/auth.service";
import {UserService} from "../../../shared/services/user.service";
import {ActivatedRoute} from "@angular/router";
import {QualificationUtil} from "../../../shared/utils/qualification.util";
import {ClientsType} from "../../../../types/clients.type";

@Component({
  selector: 'app-trainer-details',
  templateUrl: './trainer-details.component.html',
  styleUrls: ['./trainer-details.component.css']
})
export class TrainerDetailsComponent implements OnInit {

  trainers: any;

  trainerInfoForm = this.fb.group({
    firstName: [''],
    lastName: [''],
    phoneNumber: [''],
    email: ['', Validators.required],
    qualification: ['']
  })

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private activatedRoute: ActivatedRoute,
              private userService: UserService) {
  }

  ngOnInit(): void {
    // this.trainerInfo = this.authService.getUserInfo();
    this.activatedRoute.params.subscribe(params => {
      this.userService.getOneUser(params['id'])
        .subscribe(data => {

          this.trainers = data;
          this.trainers.qualificationStr = QualificationUtil.getQualificationName(this.trainers.qualificationId);

          const trainerInfo = {
            firstName: this.trainers.firstName ? this.trainers.firstName : '',
            lastName: this.trainers.lastName ? this.trainers.lastName : '',
            phoneNumber: this.trainers.phoneNumber ? this.trainers.phoneNumber : '',
            email: this.trainers.email ? this.trainers.email : '',
            qualification: this.trainers.qualificationStr ? this.trainers.qualificationStr : ''
          }

          this.trainerInfoForm.setValue(trainerInfo);

        });
    });
  }

}
