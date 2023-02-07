import { Sala } from "./Sala";

export interface Proiezione{
  id?:number;
  nome:string;
  regista:string;
  anno:number;
  minDurata:number;
  dataProiezione:string;
  dataFineProiezione:string;
  salaNum:number;
}
