import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Cinema } from './models/Cinema';
import { CinemaService } from './service/cinema.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  cinemaList:Cinema[] = [];

  constructor(private cinemaService:CinemaService){}

  ngOnInit(): void {
    this.getCinemas();
  }

  getCinemas():void{
    this.cinemaService.getAllCinema()
    .subscribe({
      next:(res)=>{
        console.log(res);
        this.cinemaList=res;
      },
      error: (error:HttpErrorResponse)=>{
        console.log(error);

      }
    })
  }

}
