package com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sale")
public class Sala{

    @Id
    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean tecnologiaIMAX;

    private int posti;
    @ManyToOne
    @JoinColumn(name="cinema_id")
    private Cinema cinema;

    private List<Proiezione> inProgrammazione;

}
