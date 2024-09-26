import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {SessionService} from "../../../shared/services/session.service";

@Component({
  selector: 'app-session-add',
  templateUrl: './session-add.component.html',
  styleUrls: ['./session-add.component.css']
})
export class SessionAddComponent implements OnInit {

  sessionForm = this.fb.group({
    sessionDate: ['', Validators.required],
    startTime: ['', Validators.required],
    endTime: ['', Validators.required],
    description: ['', Validators.required],
  })

  constructor(private fb: FormBuilder,
              private sessionService: SessionService,
              private router: Router) { }

  ngOnInit(): void {
  }

  create() {
    if (this.sessionForm.valid && this.sessionForm.value.sessionDate && this.sessionForm.value.startTime
      && this.sessionForm.value.endTime && this.sessionForm.value.description) {
      this.sessionService.createSession(this.sessionForm.value.sessionDate, this.sessionForm.value.startTime,
        this.sessionForm.value.endTime, this.sessionForm.value.description)
        .subscribe(data => {
          console.log(data);
          this.router.navigate(['/session']);
        })
    }
  }

}
