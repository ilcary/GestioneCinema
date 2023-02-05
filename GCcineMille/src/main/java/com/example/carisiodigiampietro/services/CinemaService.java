package com.example.carisiodigiampietro.services;

import com.example.carisiodigiampietro.entity.Cinema;
import com.example.carisiodigiampietro.exception.NotFoundException;
import com.example.carisiodigiampietro.repository.CinemaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {

    @Autowired
    private CinemaRepo repository;

    public Cinema save(Cinema x) {
        return repository.save(x);
    }

    public List<Cinema> getAll() {
        return repository.findAll();
    }

    public Cinema getById(Long id) {

        Optional<Cinema> cinema = repository.findById(id);

        if(!cinema.isPresent())
            throw new NotFoundException("Cinema not available");

        return cinema.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}