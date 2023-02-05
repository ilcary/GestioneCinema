package com.example.carisiodigiampietro.services;

import com.example.carisiodigiampietro.entity.Film;
import com.example.carisiodigiampietro.exception.NotFoundException;
import com.example.carisiodigiampietro.repository.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FilmService {

    @Autowired
    private FilmRepo repository;

    public Film save(Film x) {
        return repository.save(x);
    }

    public List<Film> getAll() {
        return repository.findAll();
    }

    public Film getById(Long id) {

        Optional<Film> film = repository.findById(id);

        if(!film.isPresent())
            throw new NotFoundException("Film not available");

        return film.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}