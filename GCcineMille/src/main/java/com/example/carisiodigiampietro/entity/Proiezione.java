package com.example.carisiodigiampietro.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "proiezioni")
public class Proiezione extends Film{

    @Builder(builderMethodName = "proiezioneBuilder")
    public Proiezione(String name, String regista, int anno, LocalDateTime dataProiezione, Sala sala) {
        super(name, regista, anno);
        this.dataProiezione = dataProiezione;
        this.sala = sala;
    }

    private LocalDateTime dataProiezione;


    @ManyToOne
    @JoinColumn(name = "sala_id")
    @JsonBackReference
    private Sala sala;

}
