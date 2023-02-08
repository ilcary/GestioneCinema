package com.example.carisiodigiampietro.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="sale")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean tecnologiaIMAX;

    public Boolean getTecnologiaIMAX() {
        return tecnologiaIMAX;
    }

    private Integer posti;

    // @JsonBackReference is used to prevent serialization of this relationship in JSON format
    // prevent infinite recursion in serialization
    @ManyToOne()
    @JoinColumn(name = "cinema_id")
    @JsonBackReference
    private Cinema cinema;

    @OneToMany(mappedBy = "sala",fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Proiezione> inProgrammazione;

}
