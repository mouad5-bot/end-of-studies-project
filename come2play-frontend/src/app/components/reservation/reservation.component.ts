import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {ReservationService} from "../../services/reservation.service";
import { ReservationClass} from "../../models/entities/reservation";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent {

  public addReservation = new ReservationClass();

  constructor(private reservationService: ReservationService,
    private router: Router) {}

  submitReservation() {
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
