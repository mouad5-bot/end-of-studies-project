import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {map, Observable} from "rxjs";
import {environment} from "../models/environment";
import {JwtAuthenticationResponse} from "../models/jwt-auth-response";
import {authUtils} from "../authUtils";
import {UserClass} from "../models/entities/user";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public isLoggedIn = false;
  redirectUrl: string | undefined;
  private apiUrl: string = environment.apiUrl + "/api/v1/auth";

  constructor(private http: HttpClient, private router : Router) { }

  register(firstName: string, lastName: string, email: string, bornOn: Date, gender: string, password: string ): Observable<JwtAuthenticationResponse> {
    return this.http.post<JwtAuthenticationResponse>(this.apiUrl + "/register", {firstName, lastName ,email, bornOn, gender, password});
  }

  login(email: string, password: string): Observable<JwtAuthenticationResponse> {
    return this.http.post<JwtAuthenticationResponse>(this.apiUrl + "/login", {email, password})
      .pipe(
        map((response: JwtAuthenticationResponse) => {

            // login successful if there's a jwt token in the response
            if (response.accessToken && response.refreshToken) {

              // retrieve the user info
              this.me(response.accessToken).subscribe({
                next: (user: UserClass) => {
                  authUtils.setLoggedCredentials(user, response);

                  if (authUtils.hasRole("ROLE_PLAYER"))
                    this.router.navigate(['']);
                  else if (authUtils.hasRole("ROLE_STADIUM_MANAGER"))
                    this.router.navigate(['/dashboard']);
                  else if (authUtils.hasRole("ROLE_ADMIN"))
                    this.router.navigate(['/dashboard']);
                }
              });
            }
            this.isLoggedIn = true;
            return response;
          }
        ));
  }

  me(access_token: string): Observable<UserClass> {
    return this.http.get<UserClass>(this.apiUrl + "/me", {headers: {Authorization: `Bearer ${access_token}`}})
  }

  logout() {
    this.isLoggedIn = false;
    authUtils.logout();
  }

  getRefreshToken(){
    return authUtils.getRefreshToken();
  }

  refreshToken(refresh_token: string | null): Observable<JwtAuthenticationResponse> {
    return this.http.get<JwtAuthenticationResponse>(this.apiUrl + "/token/refresh", {headers: {Authorization: `Bearer ${refresh_token}`}})
      .pipe(
        map((response: JwtAuthenticationResponse) => {
          if (response) {
            authUtils.setAccessToken(response.accessToken);
          }
          return response;
        })
      );
  }
}
