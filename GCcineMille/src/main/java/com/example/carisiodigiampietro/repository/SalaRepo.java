package com.example.carisiodigiampietro.repository;

import com.example.carisiodigiampietro.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepo extends JpaRepository<Sala, Long> {
}
