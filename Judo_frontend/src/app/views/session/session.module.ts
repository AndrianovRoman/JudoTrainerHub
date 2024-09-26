import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SessionRoutingModule } from './session-routing.module';
import {SessionComponent} from "./session.component";
import { SessionDetailsComponent } from './session-details/session-details.component';
import {ReactiveFormsModule} from "@angular/forms";
import { SessionAddComponent } from './session-add/session-add.component';


@NgModule({
  declarations: [
    SessionComponent,
    SessionDetailsComponent,
    SessionAddComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SessionRoutingModule
  ]
})
export class SessionModule { }
