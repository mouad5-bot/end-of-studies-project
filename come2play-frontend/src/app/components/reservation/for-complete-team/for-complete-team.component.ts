import {Component} from '@angular/core';
import {TeamClass} from "../../../models/entities/team";
import {TeamService} from "../../../services/team.service";
import {Router} from "@angular/router";
import {TeamType} from "../../../models/enums/team-type";

@Component({
  selector: 'app-for-complete-team',
  templateUrl: './for-complete-team.component.html',
  styleUrls: ['./for-complete-team.component.css']
})
export class ForCompleteTeamComponent {

  public addTeam = new TeamClass();
  public selectedType: TeamType | undefined;
  public types :string[] = Object.values(TeamType);
  public myImage: File | undefined;


  constructor(private teamService: TeamService,
              private router: Router) {}

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
        this.router.navigate(['/nonComplete']);
      },
      (error) => {
        alert("An error occurred")
      }
    )
  }

}
