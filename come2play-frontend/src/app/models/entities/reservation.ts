import {TeamClass} from "./team";

export class ReservationClass {
  id?: number;
  dateOfMatch?: Date;
  numberPhone?: number;
  team1?: TeamClass;
  team2?: TeamClass;
}
export interface IReservation{
  id?: number;
  dateOfMatch?: Date;
  numberPhone?: number;
  team1?: TeamClass;
  team2?: TeamClass;
}


