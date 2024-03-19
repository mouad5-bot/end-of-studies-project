import { Injectable } from '@angular/core';
import {environment} from "../models/environment";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {map, Observable} from "rxjs";
import {IStadium, StadiumClass} from "../models/entities/stadium";

@Injectable({
  providedIn: 'root'
})
export class StadiumService {

  private apiUrl: string = environment.apiUrl + "/api/v1/stadium/";

  constructor(private http: HttpClient, private router : Router) { }

  public getAllStadiums(): Observable<IStadium[]> {
    return this.http.get<IStadium[]>(this.apiUrl + "getAll").pipe(
      map(stadiums => {
        return stadiums.map(stadium => {
          stadium.stadiumImage = 'data:image/png;base64,' + stadium.stadiumImage;
          return stadium;
        });
      })
    );
  }

  public addStadium(stadium: StadiumClass): Observable<IStadium>{
    return this.http.post(this.apiUrl + "add", stadium);
  }
}
