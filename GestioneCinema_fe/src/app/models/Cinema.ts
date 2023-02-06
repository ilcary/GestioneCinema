import { Film } from "./Film";
import { Sala } from "./Sala";

export interface Cinema{
  id?:number;
  name:string;
  sale:Sala[];
  catalogoFilm:Film[];
}
