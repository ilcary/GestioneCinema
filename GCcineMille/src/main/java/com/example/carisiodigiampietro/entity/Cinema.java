package com.example.carisiodigiampietro.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="cinemas")
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "cinema",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Sala> sale;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Film> catalogoFilm;

    public void addSalaToCinema(Sala s){
        sale.add(s);
    }

    public void removeSalaFromCinema(Sala s){
        if(sale.contains(s)){
            sale.remove(s);
        }else{
            System.out.println("Sala non presente nel cinema");
        }

    }

    public void addFilmToCatalogo(Film f){
        catalogoFilm.add(f);
    }

    public void removeFilmFromCatalogo(Film f){
        if(catalogoFilm.contains(f)){
            catalogoFilm.remove(f);
        }else{
            System.out.println("Film non presente nel catalogo del cinema");
        }

    }


}
