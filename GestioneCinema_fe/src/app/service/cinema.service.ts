import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cinema } from '../models/Cinema';
import { apiUrl } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Proiezione } from '../models/Proiezione';

@Injectable({
  providedIn: 'root'
})
export class CinemaService {

  constructor(private http:HttpClient) { }

  getCinemaById(id: number):Observable<Cinema>{
    return this.http.get<Cinema>(apiUrl+"/cinema/"+id);
  }

  getAllCinema():Observable<Cinema[]>{
    return this.http.get<Cinema[]>(apiUrl+"/cinema");
  }

  getFutureProiezioni(idCinema: number):Observable<Proiezione[]>{
    return this.http.get<Proiezione[]>(apiUrl+"/cinema/inProgrammazione/"+idCinema)
  }

  getStoricoProiezioni(idCinema: number):Observable<Proiezione[]>{
    return this.http.get<Proiezione[]>(apiUrl+"/cinema/storico/"+idCinema)
  }

  getProiezioniByDataInizio(idCinema: number, dataInizio:string):Observable<Proiezione[]>{
    return this.http.get<Proiezione[]>(apiUrl+"/cinema/byDataInizio/"+idCinema+"?dataInizio="+dataInizio)
  }

  getProiezioniByDataFine(idCinema: number, dataFine:string):Observable<Proiezione[]>{
    return this.http.get<Proiezione[]>(apiUrl+"/cinema/byDataFine/"+idCinema+"?dataFine="+dataFine)
  }

  getProiezioniInRange(idCinema: number, dataFrom:string, dataTo:string):Observable<Proiezione[]>{
    return this.http.get<Proiezione[]>(apiUrl+"/cinema/byRange/"+idCinema+"?dataFrom="+dataFrom+"&dataTo="+dataTo)
  }

  saveCinema(cinemaName:string):Observable<Cinema>{
    return this.http.post<Cinema>(apiUrl+"/cinema?name="+cinemaName,{})
  }

  updateCinema(cinema:Cinema):Observable<Cinema>{
    return this.http.put<Cinema>(apiUrl + '/cinema/'+cinema.id, cinema);
  }

  deleteCinemaById(id:number):Observable<Cinema>{
    return this.http.delete<Cinema>(apiUrl + '/cinema/'+id)
  }

}
