import { Injectable } from '@angular/core';
import {IReservation, ReservationClass} from "../models/entities/reservation";
import {Observable} from "rxjs";
import {environment} from "../models/environment";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Route, Router} from "@angular/router";
import {IReservationRequest, ReservationRequestClass} from "../models/entities/request/reservation.request";
import {ITeam} from "../models/entities/team";
import {IReservationResponse} from "../models/entities/response/reservation.response";

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private apiUrl: string = environment.apiUrl + "/api/v1/reservation/";

  constructor(private http: HttpClient, router: Router) { }

  addReservation(reservation: ReservationRequestClass): Observable<IReservationRequest> {
    return this.http.post(this.apiUrl + "add", reservation);
  }
  public getAllReservation(): Observable<IReservationResponse[]> {
    return this.http.get<IReservationResponse[]>(this.apiUrl + "getAll");
  }

  public getPaginatedReservations(page: number, size: number): Observable<IReservationResponse[]> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())

    return this.http
      .get<IReservationResponse[]>(this.apiUrl + "getAll",{params} );
  }

  approveReservation(id: number) {
    return this.http.post<void>(`${this.apiUrl}${id}/approved`, {});
  }

  delete(id: number | undefined) {
    return this.http.delete(`${this.apiUrl}${id}/delete`, {})
  }
}
