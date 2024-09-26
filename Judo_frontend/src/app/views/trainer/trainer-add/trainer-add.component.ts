import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {TrainersService} from "../../../shared/services/trainers.service";
import {QualificationService} from "../../../shared/services/qualification.service";

@Component({
  selector: 'app-trainer-add',
  templateUrl: './trainer-add.component.html',
  styleUrls: ['./trainer-add.component.css']
})
export class TrainerAddComponent implements OnInit {

  // qualifications: any;

  trainerForm = this.fb.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    phoneNumber: ['', Validators.required],
    email: ['', [Validators.email, Validators.required]],
    password: ['', Validators.required],
    qualificationId: '',
  })

  constructor(private fb: FormBuilder,
              private trainerService: TrainersService,
              private qualificationService: QualificationService,
              private router: Router) { }

  ngOnInit(): void {
    // this.qualificationService.getQualifications()
    //   .subscribe(data => {
    //     this.qualifications = data;
    //     console.log(this.qualifications);
    //   });
  }

  create() {
    if (this.trainerForm.valid && this.trainerForm.value.firstName && this.trainerForm.value.lastName
      && this.trainerForm.value.phoneNumber && this.trainerForm.value.email && this.trainerForm.value.password
      && this.trainerForm.value.qualificationId) {
      this.trainerService.createTrainer(this.trainerForm.value.firstName, this.trainerForm.value.lastName,
        this.trainerForm.value.phoneNumber, this.trainerForm.value.email, this.trainerForm.value.password,
        this.trainerForm.value.qualificationId)
        .subscribe(data => {
          console.log(data);
          this.router.navigate(['/trainer']);
        })
    }
  }

}
