package com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.repo;

import com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepo extends JpaRepository<Cinema, Long> {
}
