package com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.repo;

import com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.models.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepo extends JpaRepository<Sala, Long> {
}
