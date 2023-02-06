package com.example.carisiodigiampietro.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "proiezioni")
public class Proiezione extends Film{

    @Builder(builderMethodName = "proiezioneBuilder")
    public Proiezione(String name, String regista, int anno, LocalDateTime dataProiezione, Sala sala, Integer minDurata) {
        super(name, regista, anno, minDurata);
        this.dataProiezione = dataProiezione;
        this.dataFineProiezione = dataProiezione.plusMinutes(minDurata);
        this.sala = sala;
    }

    private LocalDateTime dataProiezione;

    private LocalDateTime dataFineProiezione;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    @JsonBackReference
    private Sala sala;

//    private LocalDateTime fromStringHHmmToLdt(String data){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//        return LocalDateTime.parse(data, formatter);
//    }

}
