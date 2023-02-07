import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiUrl } from 'src/environments/environment';
import { Proiezione } from '../models/Proiezione';

@Injectable({
  providedIn: 'root'
})
export class ProiezioneService {

  constructor(private http:HttpClient) { }

  getProiezioneById(id: number):Observable<Proiezione>{
    return this.http.get<Proiezione>(apiUrl+"/proiezioni/"+id);
  }

  getAllProiezione():Observable<Proiezione[]>{
    return this.http.get<Proiezione[]>(apiUrl+"/proiezioni");
  }

  saveProiezione(filmId:number, salaId:number, data:string):Observable<Proiezione>{
    return this.http.post<Proiezione>(apiUrl+"/proiezioni?filmId="+filmId+"&salaId="+salaId+"&data="+data, {})
  }

  updateProiezione(proiezione:Proiezione):Observable<Proiezione>{
    return this.http.put<Proiezione>(apiUrl + '/proiezioni/'+proiezione.id, proiezione);
  }

  deleteProiezioneById(id:number):Observable<Proiezione>{
    return this.http.delete<Proiezione>(apiUrl + '/proiezioni/'+id)
  }
}
