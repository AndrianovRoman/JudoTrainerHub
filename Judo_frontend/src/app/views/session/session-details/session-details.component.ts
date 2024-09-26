import { Component, OnInit } from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {AuthService} from "../../../core/auth/auth.service";
import {SessionService} from "../../../shared/services/session.service";
import {ActivatedRoute} from "@angular/router";
import {UserInfoType} from "../../../../types/user-info.type";
import {SessionsType} from "../../../../types/sessions.type";

@Component({
  selector: 'app-session-details',
  templateUrl: './session-details.component.html',
  styleUrls: ['./session-details.component.css']
})
export class SessionDetailsComponent implements OnInit {

  session: any;

  sessionForm = this.fb.group({
    sessionDate: [''],
    startTime: [''],
    endTime: [''],
    description: ['']
  })

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private activatedRoute: ActivatedRoute,
              private sessionService: SessionService) { }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params => {
      this.sessionService.getOneSession(params['id'])
        .subscribe(data => {

          this.session = data;

          const sessionInfo = {
            sessionDate: this.session.sessionDate ? this.session.sessionDate : '',
            startTime: this.session.startTime ? this.session.startTime : '',
            endTime: this.session.endTime ? this.session.endTime : '',
            description: this.session.description ? this.session.description : '',
          }

          this.sessionForm.setValue(sessionInfo);

        });
    });
  }

  updateSession() {
    if (this.sessionForm.valid) {

      const paramObject: SessionsType = {
        id: this.session.id,
      };

      if(this.sessionForm.value.sessionDate) {
        paramObject.sessionDate = this.sessionForm.value.sessionDate;
      }
      if(this.sessionForm.value.startTime) {
        paramObject.startTime = this.sessionForm.value.startTime;
      }
      if(this.sessionForm.value.endTime) {
        paramObject.endTime = this.sessionForm.value.endTime;
      }
      if(this.sessionForm.value.description) {
        paramObject.description = this.sessionForm.value.description;
      }

      this.sessionService.updateSession(paramObject, this.session.id)
        .subscribe(data => {
          console.log(data);
          this.sessionForm.markAsPristine();
        });
    }
  }

}
