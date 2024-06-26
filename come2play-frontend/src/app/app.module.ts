import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { HomeComponent } from './pages/home/home.component';
import {CommonModule, NgOptimizedImage} from "@angular/common";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorModule} from '@angular/material/paginator';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { StoreRouterConnectingModule } from '@ngrx/router-store';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import {MatSelectModule} from "@angular/material/select";
import { LoginComponent } from './pages/account/auth/login/login.component';
import { RegisterComponent } from './pages/account/auth/register/register.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import {TokenInterceptor} from "./core/token.interceptor";
import { ProfileComponent } from './pages/account/profile/profile.component';
import { ReservationComponent } from './components/reservation/reservation.component';
import { StadeComponent } from './components/stade/stade.component';
import { ForCompleteTeamComponent } from './components/reservation/for-complete-team/for-complete-team.component';
import { ForNonCompleteTeamComponent } from './components/reservation/for-non-complete-team/for-non-complete-team.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ManageReservationComponent } from './components/reservation/manage-reservation/manage-reservation.component';
import {StadeDetailsComponent} from "./components/stade/stade-details/stade-details.component";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    LandingPageComponent,
    ProfileComponent,
    ReservationComponent,
    StadeComponent,
    ForCompleteTeamComponent,
    ForNonCompleteTeamComponent,
    DashboardComponent,
    ManageReservationComponent,
    StadeDetailsComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    StoreModule.forRoot({}, {}),
    EffectsModule.forRoot([]),
    StoreRouterConnectingModule.forRoot(),
    MatSelectModule,
    StoreDevtoolsModule.instrument({maxAge: 25, logOnly: !isDevMode()}),
    NgOptimizedImage
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
