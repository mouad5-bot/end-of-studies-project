import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ReservationService} from "../../services/reservation.service";
import {ITeam} from "../../models/entities/team";
import {TeamService} from "../../services/team.service";
import {Observable} from "rxjs";
import {ReservationRequestClass} from "../../models/entities/request/reservation.request";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit{

  public addReservation = new ReservationRequestClass();
  teamList$: Observable<ITeam[]> | undefined;
  public selectedTeam1: number | undefined;
  public selectedTeam2: number | undefined;
  public dateOfMatch: Date | undefined;
  public numberPhone: string | undefined;

  constructor(private reservationService: ReservationService,
    private teamService: TeamService,
    private router: Router) {}

  ngOnInit(): void {
    this.teamList$ = this.getMyTeams();
  }

  getMyTeams(): Observable<ITeam[]> {
    return this.teamService.getMyTeams();
  }
  submitReservation() {
    this.addReservation.team1 = Number(this.selectedTeam1);
    this.addReservation.team2 = Number(this.selectedTeam2);
    this.addReservation.numberPhone = this.numberPhone;
    this.addReservation.dateOfMatch = this.dateOfMatch;

    console.log("han team1 : " + this.addReservation.team1);
    console.log("han team2 : " + this.addReservation.team2);
    console.log("han num : " + this.addReservation.numberPhone);
    console.log("han date : " + this.addReservation.dateOfMatch);

    console.log("han team : " , this.addReservation);

    this.reservationService.addReservation(this.addReservation).subscribe(
      (reservation) => {
        alert("Reservation added successfully!")
      },
      (error) => {
        alert("An error occurred")
      }
    )
  }
}
