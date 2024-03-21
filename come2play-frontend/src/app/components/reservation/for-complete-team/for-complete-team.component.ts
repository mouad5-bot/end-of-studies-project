import {Component, OnInit} from '@angular/core';
import {TeamClass} from "../../../models/entities/team";
import {TeamService} from "../../../services/team.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TeamType} from "../../../models/enums/team-type";

@Component({
  selector: 'app-for-complete-team',
  templateUrl: './for-complete-team.component.html',
  styleUrls: ['./for-complete-team.component.css']
})
export class ForCompleteTeamComponent implements OnInit{
  public addTeam = new TeamClass();
  public selectedType: TeamType | undefined;
  public types :string[] = Object.values(TeamType);
  public myImage: File | undefined;
  public stadiumId = 0;

  constructor(private teamService: TeamService,
              private router: Router,
              private route: ActivatedRoute) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      console.log(params)
      let id = +( params.get('id') ?? 0);
      this.stadiumId = id;
    })
  }

  saveImage(event: any){
    this.myImage = event.target.files[0];
  }
  submitTeam() {
    if (this.myImage == undefined){
      alert("selected image is undefined !");
      return;
    }
    const formData = new FormData();
    formData.append('file', this.myImage)
    this.addTeam.teamType = this.selectedType;
    formData.append('team', JSON.stringify(this.addTeam));

    this.teamService.addTeam(formData).subscribe(
      (team) => {
        alert("Team added successfully!")
        this.router.navigate(['/nonComplete', this.stadiumId]);
      },
      (error) => {
        alert("An error occurred")
      }
    )
  }

}
