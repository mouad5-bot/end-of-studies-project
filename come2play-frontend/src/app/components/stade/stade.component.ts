import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {IStadium} from "../../models/entities/stadium";
import {StadiumService} from "../../services/stadium.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-stade',
  templateUrl: './stade.component.html',
  styleUrls: ['./stade.component.css'],
})
export class StadeComponent implements OnInit{
  stadiumList!: IStadium[];

  constructor(public stadiumService: StadiumService , private cdr:ChangeDetectorRef) {}

  ngOnInit(){
    this.getAllStadiums();
    console.log(this.stadiumList)
  }

  getAllStadiums(){
    this.stadiumService.getAllStadiums().subscribe(
      (res: IStadium[]) => {
        this.stadiumList = res;
      },
      (error) => {
        alert("error to fetch stadiums");
      }
    )
  }
}
