import {TeamClass} from "../team";

export class ReservationResponseClass {
  id?: number;
  reservedAt?: Date;
  dateOfMatch?: Date;
  status?: string;
  requestForTeam?: string;
  numberPhone?: number;
  team1?: string;
  team2?: string;
  stadiumName?: string;

}
export interface IReservationResponse{
  id?: number;
  reservedAt?: Date;
  dateOfMatch?: Date;
  status?: string;
  requestForTeam?: string;
  numberPhone?: number;
  team1?: string;
  team2?: string;
  stadiumName?: string;
}


