export class StadiumClass implements IStadium {
  constructor(
    public id?: number,
    public stadeName?: string,
    public city?: string,
    public stadiumImage?: string,
    public teamType?: string,
    public price?: number
  ) {}
}

export interface IStadium {
  id?: number;
  stadeName?: string;
  city?: string;
  teamType?: string;
  price?: number;
  stadiumImage?: string;
}


