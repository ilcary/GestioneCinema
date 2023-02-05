package com.example.carisiodigiampietro.repository;

import com.example.carisiodigiampietro.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepo extends JpaRepository<Film, Long> {
}
