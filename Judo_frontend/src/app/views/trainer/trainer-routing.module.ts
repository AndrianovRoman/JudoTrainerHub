import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TrainerComponent} from "./trainer.component";
import {TrainerDetailsComponent} from "./trainer-details/trainer-details.component";
import {TrainerAddComponent} from "./trainer-add/trainer-add.component";

const routes: Routes = [
  {path: 'trainer', component: TrainerComponent},
  {path: 'trainer/:id', component: TrainerDetailsComponent},
  {path: 'trainer-create', component: TrainerAddComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TrainerRoutingModule { }
