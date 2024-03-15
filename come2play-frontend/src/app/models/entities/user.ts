export class UserClass implements IUser{
  id?: number;
  token?: string;
  firstName?: string;
  lastName?: string;
  email?: string;
  bornOn?: Date;
  gender?: string;
  authorities?: string[];
}
export interface IUser{
  id?:number,
  token?: string;
  firstName?: string;
  lastName?: string;
  email?: string;
  bornOn?: Date;
  gender?: string;
  authorities?: string[];
}
