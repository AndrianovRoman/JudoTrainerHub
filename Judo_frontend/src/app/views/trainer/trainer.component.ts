import {Component, OnInit} from '@angular/core';
import {OwlOptions} from "ngx-owl-carousel-o";
import {TrainersService} from "../../shared/services/trainers.service";
import {AuthService} from "../../core/auth/auth.service";
import {QualificationUtil} from "../../shared/utils/qualification.util";
import {ClientsType} from "../../../types/clients.type";

@Component({
  selector: 'app-trainer',
  templateUrl: './trainer.component.html',
  styleUrls: ['./trainer.component.css']
})
export class TrainerComponent implements OnInit {

  trainers: ClientsType[] = [];
  role: string | null = 'CLIENT';

  customOptionsReviews: OwlOptions = {
    loop: true,
    mouseDrag: false,
    touchDrag: false,
    pullDrag: false,
    margin: 20,
    dots: false,
    navSpeed: 700,
    navText: ['', ''],
    responsive: {
      0: {
        items: 1
      },
      400: {
        items: 2
      },
      740: {
        items: 3
      }
    },
    nav: false
  }

  constructor(private trainerService: TrainersService,
              private authService: AuthService) {
    this.role = this.authService.getIsRole();
    // console.log(this.role);
  }

  ngOnInit(): void {
    this.trainerService.getTrainers()
      .subscribe(data => {
        this.trainers = data.map(item => {
          item.qualificationStr = QualificationUtil.getQualificationName(item.qualificationId);
          return item;
        })
        // console.log(this.trainers);
      })
  }

}
