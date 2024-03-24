import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
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
  teamList: ITeam[] | undefined;
  public selectedTeam1: number | undefined;
  public selectedTeam2: number | undefined;
  public dateOfMatch: Date | undefined;
  public numberPhone: string | undefined;
  public stadiumId: number = 0;

  constructor(private reservationService: ReservationService,
    private teamService: TeamService,
    private route: ActivatedRoute,
    private router: Router) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      let id = +( params.get('id') ?? 0);
      this.stadiumId = id;
    })

    this.getMyTeams();
  }

  getMyTeams() {
    this.teamService.getMyTeams().subscribe(
      (teams) => {
        this.teamList = teams;
        console.log(this.teamList);
        console.log(teams);
      }
    );
  }
  submitReservation() {
    this.addReservation.team1 = Number(this.selectedTeam1);
    this.addReservation.team2 = Number(this.selectedTeam2);
    this.addReservation.numberPhone = this.numberPhone;
    this.addReservation.dateOfMatch = this.dateOfMatch;
    this.addReservation.stadiumId = this.stadiumId;

    console.log("han stadium ayaa" , this.stadiumId);

    this.reservationService.addReservation(this.addReservation).subscribe(
      (reservation) => {
        alert("Reservation added successfully!")
        this.router.navigate(['/landingPage']);
      },
      (error) => {
        alert("An error occurred")
      }
    )
  }
}
