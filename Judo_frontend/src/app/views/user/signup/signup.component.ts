import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {AuthService} from "../../../core/auth/auth.service";
import {Router} from "@angular/router";
import {LoginResponseType} from "../../../../types/login-response.type";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm = this.fb.group({
    firstName: ['', Validators.required],
    lastName: ['', Validators.required],
    phoneNumber: ['', Validators.required],
    email: ['', [Validators.email, Validators.required]],
    password: ['', Validators.required],
  })

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  signup(): void {
    if (this.signupForm.valid && this.signupForm.value.firstName && this.signupForm.value.lastName
      && this.signupForm.value.phoneNumber && this.signupForm.value.email && this.signupForm.value.password) {
      this.authService.signup(this.signupForm.value.firstName, this.signupForm.value.lastName,
        this.signupForm.value.phoneNumber, this.signupForm.value.email, this.signupForm.value.password)
        .subscribe({
          next: data => {

            const signupResponse = data;

            if (signupResponse && signupResponse.email && this.signupForm.value.password) {
              this.authService.login(signupResponse.email, this.signupForm.value.password)
                .subscribe({
                  next: (data: LoginResponseType) => {

                    const loginResponse = data as LoginResponseType;
                    if (loginResponse.token && loginResponse.role && loginResponse.id) {
                      this.authService.setTokens(loginResponse.token, loginResponse.role, loginResponse.id);
                      this.router.navigate(['/']);
                    }
                  }
                })
            }
          }
        })
    }
  }

}
