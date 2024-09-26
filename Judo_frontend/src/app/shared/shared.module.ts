import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CarouselModule} from "ngx-owl-carousel-o";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    CarouselModule,
  ],
  exports: [
    CarouselModule,

  ]
})
export class SharedModule { }
