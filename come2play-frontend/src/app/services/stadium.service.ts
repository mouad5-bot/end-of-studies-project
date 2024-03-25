import { Injectable } from '@angular/core';
import {environment} from "../models/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Router} from "@angular/router";
import {map, Observable} from "rxjs";
import {IStadium, StadiumClass} from "../models/entities/stadium";
import {IReservationResponse} from "../models/entities/response/reservation.response";

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
  public getPaginatedStadiums(page: number, size: number): Observable<IStadium[]> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())

    return this.http
      .get<IStadium[]>(this.apiUrl + "getAll",{params} );
  }

  public addStadium(stadium: StadiumClass): Observable<IStadium>{
    return this.http.post(this.apiUrl + "add", stadium);
  }
}
