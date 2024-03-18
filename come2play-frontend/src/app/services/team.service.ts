import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../models/environment";
import {Router} from "@angular/router";
import {ITeam, TeamClass} from "../models/entities/team";

@Injectable({
  providedIn: 'root'
})
export class TeamService {
  private apiUrl: string = environment.apiUrl + "/api/v1/team/";

  constructor(private http: HttpClient, private router : Router) { }

  public getAllTeams(): Observable<ITeam[]> {
    return this.http.get<ITeam[]>(this.apiUrl + "getAll");
  }

  public addTeam(team: TeamClass): Observable<ITeam>{
    return this.http.post(this.apiUrl + "add", team);
  }

  public getPaginatedTeams(page: number, size: number): Observable<ITeam[]> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())

    return this.http
      .get<ITeam[]>(this.apiUrl + "getAll",{params} );
  }
}
