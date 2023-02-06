package com.example.carisiodigiampietro.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "films")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Film {

    @Builder(builderMethodName = "filmBuilder")
    public Film(String nome,String regista, int anno, Integer minDurata) {
        this.nome = nome;
        this.regista = regista;
        this.anno = anno;
        this.minDurata = minDurata;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String regista;

    private Integer anno;

    private Integer minDurata;
}