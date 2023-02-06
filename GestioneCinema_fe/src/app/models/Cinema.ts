import { Sala } from "./Sala";

export interface Cinema{
  id?:number;
  nome:string;
  sale:Sala[];
}
