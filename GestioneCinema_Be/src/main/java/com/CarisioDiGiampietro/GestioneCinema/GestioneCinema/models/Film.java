package com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.models;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "films")
public class Film {

    @Id
    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String regista;

    private int anno;
}
