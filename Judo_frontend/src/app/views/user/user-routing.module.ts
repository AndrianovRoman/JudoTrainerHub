import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {SignupComponent} from "./signup/signup.component";
import {UserDetailsComponent} from "./user-details/user-details.component";

const routes: Routes = [
  {path: 'user/login', component: LoginComponent},
  {path: 'user/signup', component: SignupComponent},
  {path: 'user/details', component: UserDetailsComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
