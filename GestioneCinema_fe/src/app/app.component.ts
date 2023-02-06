import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Cinema } from './models/Cinema';
import { Proiezione } from './models/Proiezione';
import { Sala } from './models/Sala';
import { CinemaService } from './service/cinema.service';
import { SalaService } from './service/sala.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  visibleCinemas: boolean = true;
  cinemaList: Cinema[] = [];
  salaList: Sala[] = [];
  proiezioniFuture: Proiezione[] = [];
  nomeCinema:string="";

  constructor(private cinemaService: CinemaService, private salaService: SalaService) { }

  ngOnInit(): void {
    this.getCinemas();
  }

  getCinemas(): void {
    this.cinemaService.getAllCinema()
      .subscribe({
        next: (res) => {
          console.log(res);
          this.cinemaList = res;
        },
        error: (error: HttpErrorResponse) => {
          console.log(error);

        }
      })

  }

  getProiezioniFuture(cinemaId: number): void {
    this.cinemaService.getFutureProiezioni(cinemaId)
      .subscribe({
        next: (res) => {
          console.log(res);
          this.proiezioniFuture = res;
        },
        error: (error: HttpErrorResponse) => {
          console.log(error);

        }
      })
  }

  seeSaleOfCinema(cinema: Cinema): void {
    this.visibleCinemas = !this.visibleCinemas;
    this.nomeCinema=cinema.name;
    if (cinema.id) {
      this.salaService.getallSalaByCinemaId(cinema.id)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.salaList = res;
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);
          }
        })
    } else {
      console.log("CinemaId is undefined");
    }
  }

  setHome():void{
    this.visibleCinemas=true;
  }


}
