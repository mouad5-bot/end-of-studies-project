import {TeamType} from "../enums/team-type";

export class TeamClass implements ITeam{
  id?: number;
  teamName?: string;
  teamType?: TeamType;
  image?: string;
  createdBy?: number;
}
export interface ITeam{
  id?: number;
  teamName?: string;
  teamType?: TeamType;
  image?: string;
  createdBy?: number;
}
