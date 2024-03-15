import { Component } from '@angular/core';
import {TeamClass} from "../../../models/entities/team";
import {TeamType} from "../../../models/enums/team-type";
import {TeamService} from "../../../services/team.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-for-non-complete-team',
  templateUrl: './for-non-complete-team.component.html',
  styleUrls: ['./for-non-complete-team.component.css']
})
export class ForNonCompleteTeamComponent {
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
        this.router.navigate(['/reservation'])
      },
      (error) => {
        alert("An error occurred")
      }
    )
  }
}
