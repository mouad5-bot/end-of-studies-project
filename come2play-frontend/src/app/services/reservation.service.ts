import { Injectable } from '@angular/core';
import {IReservation, ReservationClass} from "../models/entities/reservation";
import {Observable} from "rxjs";
import {environment} from "../models/environment";
import {HttpClient} from "@angular/common/http";
import {Route, Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private apiUrl: string = environment.apiUrl + "/api/v1/reservation/";

  constructor(private http: HttpClient, router: Router) { }

  addReservation(reservation: ReservationClass): Observable<IReservation> {
    return this.http.post(this.apiUrl + "add", reservation);
  }
}
