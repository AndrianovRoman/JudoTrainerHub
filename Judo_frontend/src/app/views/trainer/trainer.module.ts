import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TrainerRoutingModule } from './trainer-routing.module';
import {TrainerComponent} from "./trainer.component";
import {ReactiveFormsModule} from "@angular/forms";
import {SharedModule} from "../../shared/shared.module";
import { TrainerDetailsComponent } from './trainer-details/trainer-details.component';
import { TrainerAddComponent } from './trainer-add/trainer-add.component';


@NgModule({
  declarations: [
    TrainerComponent,
    TrainerDetailsComponent,
    TrainerAddComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule,
    TrainerRoutingModule
  ]
})
export class TrainerModule { }
