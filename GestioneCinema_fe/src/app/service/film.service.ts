import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { apiUrl } from 'src/environments/environment';
import { Film } from '../models/Film';

@Injectable({
  providedIn: 'root'
})
export class FilmService {

  constructor(private http:HttpClient) { }

  getFilmById(id: number):Observable<Film>{
    return this.http.get<Film>(apiUrl+"/film/"+id);
  }

  getAllFilm():Observable<Film[]>{
    return this.http.get<Film[]>(apiUrl+"/film");
  }

  saveFilm(name:string, regista:string, anno:number, cinemaId:number, minDurata:number):Observable<Film>{
    return this.http.post<Film>(apiUrl+"/film?name="+name+"&regista="+regista+"&anno="+anno+"&cinemaId="+cinemaId+"&minDurata="+minDurata, {})
  }

  updateFilm(film:Film):Observable<Film>{
    return this.http.put<Film>(apiUrl + '/film/'+film.id, film);
  }

  deleteFilmById(id:number):Observable<Film>{
    return this.http.delete<Film>(apiUrl + '/film/'+id)
  }
}
