import {TeamType} from "../enums/team-type";

export class TeamClass implements ITeam{
  teamName?: string;
  teamType?: TeamType;
  image?: string;
  createdBy?: number;
}
export interface ITeam{
  teamName?: string;
  teamType?: TeamType;
  image?: string;
  createdBy?: number;
}
