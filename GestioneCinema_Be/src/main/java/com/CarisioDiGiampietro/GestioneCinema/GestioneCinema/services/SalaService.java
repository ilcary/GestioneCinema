package com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.services;

import com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.exception.NotFoundException;
import com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.models.Sala;
import com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.repo.SalaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepo repository;

    public Sala save(Sala x) {
        return repository.save(x);
    }

    public List<Sala> getAll() {
        return repository.findAll();
    }

    public Sala getById(Long id) {

        Optional<Sala> sala = repository.findById(id);

        if(!sala.isPresent())
            throw new NotFoundException("Sala not available");

        return sala.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
