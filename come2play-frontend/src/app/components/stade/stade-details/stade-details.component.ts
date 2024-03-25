import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {PageEvent} from "@angular/material/paginator";
import {StadiumService} from "../../../services/stadium.service";
import {IStadium} from "../../../models/entities/stadium";

@Component({
  selector: 'app-stade-details',
  templateUrl: './stade-details.component.html',
  styleUrls: ['./stade-details.component.css']
})
export class StadeDetailsComponent {
  stadiumList!: IStadium[];
  currentPage: number = 0;
  pageSize:number = 3;

  constructor(private stadiumService: StadiumService, private router:Router) {
  }
  ngOnInit() {
    this.getAllStadiums();
  }

  getAllStadiums(){
    this.stadiumService.getPaginatedStadiums(this.currentPage, this.pageSize).subscribe(
      (res: IStadium[]) =>  {
        this.stadiumList = res;
      },
      (error) => {
        alert("error fetching stadiums !");
      }
    )
  }
  onPageChange(event: PageEvent): void {
    this.currentPage = event.pageIndex !== undefined ? event.pageIndex : 0;
    this.pageSize = event.pageSize !== undefined ? event.pageSize : 2;
    this.getAllStadiums();
  }
}
