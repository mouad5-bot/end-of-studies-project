import {Component, OnInit} from '@angular/core';
import {ReservationService} from "../../../services/reservation.service";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {PageEvent} from "@angular/material/paginator";
import {IReservationResponse} from "../../../models/entities/response/reservation.response";
import {Router} from "@angular/router";

@Component({
  selector: 'app-manage-reservation',
  templateUrl: './manage-reservation.component.html',
  styleUrls: ['./manage-reservation.component.css']
})
export class ManageReservationComponent implements OnInit{
  reservationList!: IReservationResponse[];
  currentPage: number = 0;
  pageSize:number = 3;

  constructor(private reservationService: ReservationService, private router:Router) {
  }

  approveReservation(id?: number){
    if (id == undefined)
      return;
    this.reservationService.approveReservation(id).subscribe(
      () => {
        this.updateStatusLocaly(id);
        alert("status updated successfully");
      },
     (error) => {
        alert(error.error.message);
      }
    );
  }

  private updateStatusLocaly(id: number) {
    let find = this.reservationList
      .find(r => r.id == id);
    if (find)
      find.status = "ACCEPTED"
  }

  ngOnInit() {
    this.getAllReservations();
  }

  getAllReservations(){
    this.reservationService.getPaginatedReservations(this.currentPage, this.pageSize).subscribe(
      (res: IReservationResponse[]) =>  {
        this.reservationList = res;
      },
      (error) => {
        alert("error fetching reservations !");
      }
    )
  }
  onPageChange(event: PageEvent): void {
    this.currentPage = event.pageIndex !== undefined ? event.pageIndex : 0;
    this.pageSize = event.pageSize !== undefined ? event.pageSize : 2;
    this.getAllReservations();
  }

  delete(id: number | undefined) {
    this.reservationService.delete(id).subscribe(
      () => {
        alert("reservation deleted successfully!");
        this.router.navigate(['dashboard/manageReservation'])
      },
      (error) => {
        alert("error deleting reservation !!");
      }
    );
  }
}
