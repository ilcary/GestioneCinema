import { Cinema } from "./Cinema";
import { Proiezione } from "./Proiezione";

export interface Sala{
  id?:number;
  tecnologiaIMAX:boolean;
  posti:number;
  cinema:Cinema;
  inProgrammazione:Proiezione[];
}
