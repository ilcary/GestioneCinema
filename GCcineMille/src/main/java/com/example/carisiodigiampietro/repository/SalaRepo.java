package com.example.carisiodigiampietro.repository;

import com.example.carisiodigiampietro.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaRepo extends JpaRepository<Sala, Long> {

@Query("select s from Sala s where s.cinema.id = ?1")
List<Sala> findSalaByCinemaId(Long id);

}