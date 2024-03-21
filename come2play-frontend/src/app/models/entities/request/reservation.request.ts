export class ReservationRequestClass {
  id?: number;
  dateOfMatch?: Date;
  numberPhone?: string;
  team1?: number;
  team2?: number;
  stadiumId?: number;

}
export interface IReservationRequest{
  id?: number;
  dateOfMatch?: Date;
  numberPhone?: string;
  team1?: number;
  team2?: number;
  stadiumId?: number;
}


