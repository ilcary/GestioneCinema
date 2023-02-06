import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiUrl } from 'src/environments/environment';
import { Sala } from '../models/Sala';

@Injectable({
  providedIn: 'root'
})
export class SalaService {

  constructor(private http:HttpClient) { }

  getSalaById(id: number):Observable<Sala>{
    return this.http.get<Sala>(apiUrl+"/sala/"+id);
  }

  getAllSala():Observable<Sala[]>{
    return this.http.get<Sala[]>(apiUrl+"/sala");
  }

  getallSalaByCinemaId(cinemaId: number):Observable<Sala[]>{
    return this.http.get<Sala[]>(apiUrl+"/sala/allSalaByCinemaId/"+cinemaId);
  }

  saveSala(posti:number, tecnologiaIMAX:boolean, cinemaId:number):Observable<Sala>{
    return this.http.post<Sala>(apiUrl+"/sala?tecnologiaIMAX="+tecnologiaIMAX+"&posti="+posti+"&cinemaId"+cinemaId, {})
  }

  updateSala(sala:Sala):Observable<Sala>{
    return this.http.put<Sala>(apiUrl + '/sala/'+sala.id, sala);
  }

  deleteSalaById(id:number):Observable<Sala>{
    return this.http.delete<Sala>(apiUrl + '/sala/'+id)
  }
}
