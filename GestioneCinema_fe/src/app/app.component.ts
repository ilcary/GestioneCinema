import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Cinema } from './models/Cinema';
import { Film } from './models/Film';
import { Proiezione } from './models/Proiezione';
import { Sala } from './models/Sala';
import { CinemaService } from './service/cinema.service';
import { ProiezioneService } from './service/proiezione.service';
import { SalaService } from './service/sala.service';
import Swal from 'sweetalert2';
import { FilmService } from './service/film.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  dataProiezione!: string;
  dataInizioFine!: string;
  nomeNuovoCinema!: string;
  cinemaAttuale!: Cinema;
  salaAttuale!: Sala;
  visibleCinemas: boolean = true;
  visibleCatalogoFilm: boolean = false;
  visibleProiezioniCinema: boolean = false;
  visibleSalas: boolean = false;
  visibleProiezioni: boolean = false;
  visibleAddFilm: boolean = false;
  cinemaList: Cinema[] = [];
  salaList: Sala[] = [];
  proiezioniFuture: Proiezione[] = [];
  proiezioni: Proiezione[] = [];
  nomeCinema: string = "";
  formFilm!: FormGroup;
  formSala!: FormGroup;

  constructor(
    private cinemaService: CinemaService,
    private salaService: SalaService,
    private proiezioneService: ProiezioneService,
    private filmService: FilmService
  ) { }

  ngOnInit(): void {
    this.getCinemas();
    this.formFilm = new FormGroup({
      nome: new FormControl(null, [Validators.required]),
      regista: new FormControl(null, [Validators.required]),
      anno: new FormControl(null, [Validators.required]),
      durata: new FormControl(null, [Validators.required])
    })

    this.formSala = new FormGroup({
      posti: new FormControl(null, [Validators.required]),
      tecnologiaIMAX: new FormControl(null, [Validators.required])
    })
  }

  //HOME CARD CINEMA

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

  saveNuovoCinema():void{
    this.cinemaService.saveCinema(this.nomeNuovoCinema)
    .subscribe({
      next: (res) => {
        console.log(res);
        this.cinemaList.push(res);
        Swal.fire(
          'Nuovo Cinema creato!',
          'Il cinema "' + res.name + ' è stato creato correttamente :)',
          'success'
        )
        this.nomeNuovoCinema="";
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);

      }
    })
  }

  deleteCinema(cinema:Cinema):void{
    Swal.fire({
      title: 'Sei sicuro di voler eliminare il cinema '+cinema.name+' ?',
      text: "Perderai tutte le sale e le proiezioni inerenti al cinema!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, sono sicuro!'
    }).then((result) => {
      if (result.isConfirmed&&cinema.id) {
        this.cinemaService.deleteCinemaById(cinema.id)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.cinemaList.splice(this.cinemaList.indexOf(res),1)
            Swal.fire(
              'Eliminato!',
              'Il cinema "'+cinema.name+'" è stato eliminato correttamente',
              'success'
            )
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);

          }
        })

      }else if (!cinema.id) {
        console.log("L'id del cinema è undefined");

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

  //SALE CINEMA

  seeSaleOfCinema(cinema: Cinema): void {
    //ci salviamo il cinema in questione così da poter utilizzare il catalogo in altre funzioni
    this.cinemaAttuale = cinema;
    this.visibleCinemas = !this.visibleCinemas;
    this.visibleSalas = !this.visibleSalas;
    this.nomeCinema = cinema.name;
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

  addSalaToCinema():void{
    console.log(this.formSala.value.tecnologiaIMAX);

    if (this.cinemaAttuale.id) {
      this.salaService.saveSala(this.formSala.value.posti,this.formSala.value.tecnologiaIMAX, this.cinemaAttuale.id)
        .subscribe({
          next: (res) => {
            console.log(res);
            //controlliamo che l'array di sale non sia null cosi da poter aggiungere la sala appena creata
            if(this.salaList){
              this.salaList.push(res);
            }else{//se null lo settiamo vuoto e gli metteremo la sua prima sala
              this.salaList= [];
              this.salaList.push(res);
            }
            Swal.fire(
              'Sala aggiunta al cinema!',
              'La sala n. ' + (this.salaList.indexOf(res)+1) + ' è stata aggiunta correttamente :)',
              'success'
            )
            this.formSala.reset();
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);
          }
        })
    } else {
      console.log("CinemaId is undefined");
    }
  }

  deleteSala(sala:Sala):void {
    Swal.fire({
      title: 'Sei sicuro di voler eliminare questa sala?',
      text: "Quest'azione non è reversibile!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, voglio eliminarla!'
    }).then((result) => {
      if (result.isConfirmed) {
        if(sala.id){
          this.salaService.deleteSalaById(sala.id)
          .subscribe({
            next: (res) => {
              console.log(res);
              this.salaList.splice(this.salaList.indexOf(res),1)
              Swal.fire(
                'Sala eliminata dal cinema!',
                'La sala è stata eliminata correttamente :(',
                'success'
              )
            },
            error: (error: HttpErrorResponse) => {
              console.log(error);

            }
          })
        }else{
          console.log("L'id della Sala è undefined");

        }
      }
    })

  }

  getProiezioniFutureBySala(sala: Sala): void {
    this.visibleSalas = !this.visibleSalas;
    this.visibleProiezioni = !this.visibleProiezioni;
    //senza fare altre chiamate al be sfruttiamo gli oggetti che abbiamo già
    this.proiezioniFuture = sala.inProgrammazione;
  }

  seeFilmToAdd(sala: Sala): void {
    this.salaAttuale = sala;
    this.visibleAddFilm = !this.visibleAddFilm;
    this.visibleSalas = !this.visibleSalas;
  }

  newProiezioneInSala(film: Film): void {
    console.log(this.dataProiezione);

    if (film.id && this.salaAttuale.id) {
      this.proiezioneService.saveProiezione(film.id, this.salaAttuale.id, this.dataProiezione)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.salaAttuale.inProgrammazione.push(res);
            Swal.fire(
              'Film aggiunto in programmazione!',
              'Il film: "' + res.nome + '" sarà proiettato il: ' + res.dataProiezione,
              'success'
            )
            this.setSaleFromProgrammazione();//toriniamo alle sale dopo aver aggiunto il film in programmazione :)
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);
          }
        })

    } else {
      if (film.id)
        console.log("L'id del film è undefined");
      if (this.salaAttuale.id)
        console.log("L'id della sala attuale è undefined");
    }
  }

  //CATALOGO CINEMA

  seeCatalogoFilm(cinema: Cinema) {
    this.cinemaAttuale = cinema;
    this.nomeCinema = cinema.name;
    this.visibleCinemas = !this.visibleCinemas;
    this.visibleCatalogoFilm = !this.visibleCatalogoFilm;

  }

  deleteFilmFromCatalogo(film: Film): void {
    if (film.id) {
      this.filmService.deleteFilmById(film.id)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.cinemaAttuale.catalogoFilm.splice(this.cinemaAttuale.catalogoFilm.indexOf(res), 1);
            Swal.fire(
              'Film rimosso dal catalogo :(',
              'Il film: "' + res.nome + '" è stato correttamente eliminato',
              'success'
            )
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);
          }
        })
    } else {
      console.log("L'id del film è undefined");

    }

  }

  addFilmToCatalogo(): void {
    if (this.cinemaAttuale.id) {
      this.filmService.saveFilm(this.formFilm.value.nome, this.formFilm.value.regista, this.formFilm.value.anno, this.cinemaAttuale.id, this.formFilm.value.durata)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.cinemaAttuale.catalogoFilm.push(res);
            Swal.fire(
              'Film aggiunto al catalogo!',
              'Il film: "' + res.nome + '" è stato correttamente aggiunto al catalogo del cinema: ' + this.cinemaAttuale.name,
              'success'
            )
            this.formFilm.reset();
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);
          }
        })
    } else {
      console.log("L'id del cinema è undefined");

    }

  }

  //PROIEZIONI COMPLESSIVE DEL CINEMA

  seeProiezioniCinema(cinema: Cinema) {
    this.cinemaAttuale = cinema;
    this.nomeCinema = cinema.name;
    this.visibleCinemas = !this.visibleCinemas;
    this.visibleProiezioniCinema = !this.visibleProiezioniCinema;
    this.getFutureProiezioni(cinema)


  }

  getFutureProiezioni(cinema: Cinema) {
    if (cinema.id) {
      this.cinemaService.getFutureProiezioni(cinema.id)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.proiezioni = res;
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);
          }
        })
    } else {
      console.log("L'id del cinema è undefined");
    }

  }

  getProiezioniPassate(cinema: Cinema): void {
    if (cinema.id) {
      this.cinemaService.getStoricoProiezioni(cinema.id)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.proiezioni = res;
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);
          }
        })
    } else {
      console.log("L'id del cinema è undefined");

    }
  }

  getProiezioniByDataInizio(cinema: Cinema, dataInizio: string): void {
    if (cinema.id) {
      this.cinemaService.getProiezioniByDataInizio(cinema.id, dataInizio)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.proiezioni = res;
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);
          }
        })
    } else {
      console.log("L'id del cinema è undefined");

    }
  }

  getProiezioniByDataFine(cinema: Cinema, dataFine: string): void {
    if (cinema.id) {
      this.cinemaService.getProiezioniByDataFine(cinema.id, dataFine)
        .subscribe({
          next: (res) => {
            console.log(res);
            this.proiezioni = res;
          },
          error: (error: HttpErrorResponse) => {
            console.log(error);
          }
        })
    } else {
      console.log("L'id del cinema è undefined");

    }
  }

  //LOGICA VISIBILITY

  //reset delle visibility per la home
  setHome(): void {
    this.visibleCinemas = true;
    this.visibleSalas = false;
    this.visibleCatalogoFilm = false;
    this.visibleProiezioniCinema = false;
    this.visibleSalas = false;
    this.visibleProiezioni = false;
    this.visibleAddFilm = false;
  }

  //visibility dalle proiezioni alle sale
  setProiezioni(): void {
    this.visibleProiezioni = false;
    this.visibleSalas = true;
  }

  //visibility dalle
  setSaleFromProgrammazione(): void {
    this.visibleAddFilm = false;
    this.visibleSalas = true;
  }


}
