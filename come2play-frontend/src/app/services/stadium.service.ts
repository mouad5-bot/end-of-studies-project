import { Injectable } from '@angular/core';
import {environment} from "../models/environment";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {map, Observable} from "rxjs";
import {IStadium, StadiumClass} from "../models/entities/stadium";
import {ApiResponseModel} from "../core/api-response.model";

@Injectable({
  providedIn: 'root'
})
export class StadiumService {

  private apiUrl: string = environment.apiUrl + "/api/v1/stadium/";

  constructor(private http: HttpClient, private router : Router) { }

  public getAllStadiums(): Observable<IStadium[]> {
    return this.http.get<IStadium[]>(this.apiUrl + "getAll");
  }

  public addStadium(stadium: StadiumClass): Observable<IStadium>{
    return this.http.post(this.apiUrl + "add", stadium);
  }
}
