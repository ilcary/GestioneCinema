package com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.repo;

import com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.models.Proiezione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProiezioneRepo extends JpaRepository<Proiezione, Long> {
}
