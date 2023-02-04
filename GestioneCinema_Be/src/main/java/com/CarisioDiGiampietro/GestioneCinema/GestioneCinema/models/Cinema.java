package com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.models;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cinemas")
public class Cinema {

    @Id
    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @OneToMany(mappedBy = "cinema")
    private List<Sala> sale;

    private List<Film> catalogo;

}
