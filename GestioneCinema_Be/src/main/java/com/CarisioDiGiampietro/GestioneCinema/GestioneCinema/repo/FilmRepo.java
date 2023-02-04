package com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.repo;

import com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.models.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepo extends JpaRepository<Film, Long> {
}
