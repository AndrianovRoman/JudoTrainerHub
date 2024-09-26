import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SessionComponent} from "./session.component";
import {SessionDetailsComponent} from "./session-details/session-details.component";
import {SessionAddComponent} from "./session-add/session-add.component";

const routes: Routes = [
  {path: 'session', component: SessionComponent},
  {path: 'session/:id', component: SessionDetailsComponent},
  {path: 'session-create', component: SessionAddComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SessionRoutingModule { }
