package com.example.carisiodigiampietro.repository;

import com.example.carisiodigiampietro.entity.Proiezione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProiezioneRepo extends JpaRepository<Proiezione, Long> {
}
