import { Component } from '@angular/core';
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

  constructor(private teamService: TeamService,
              private router: Router) {}
  submitTeam() {
    this.addTeam.teamType = this.selectedType;

    this.teamService.addTeam(this.addTeam).subscribe(
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
