import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {LoginComponent} from "./pages/account/auth/login/login.component";
import {RegisterComponent} from "./pages/account/auth/register/register.component";
import {LandingPageComponent} from "./components/landing-page/landing-page.component";
import {AuthGuard} from "./core/guards/auth.guard";
import {ProfileComponent} from "./pages/account/profile/profile.component";
import {ReservationComponent} from "./components/reservation/reservation.component";
import {StadeComponent} from "./components/stade/stade.component";
const routes: Routes = [
  { path:"login", component:LoginComponent},
  { path:"register", component:RegisterComponent},
  {
    path: "",
    component: HomeComponent,
    canActivate: [AuthGuard],
    children: [
      { path: "", component:LandingPageComponent},
      { path: "landingPage", component:LandingPageComponent},
      { path: "profile", component:ProfileComponent},
      { path: "reservation", component:ReservationComponent},
      { path: "stadium", component:StadeComponent},

    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
