package com.example.carisiodigiampietro.repository;

import com.example.carisiodigiampietro.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepo extends JpaRepository<Cinema, Long> {
}
