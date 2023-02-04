package com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.services;

import com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.exception.NotFoundException;
import com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.models.Proiezione;
import com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.repo.ProiezioneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProiezioneService {

    @Autowired
    private ProiezioneRepo repository;

    public Proiezione save(Proiezione x) {
        return repository.save(x);
    }

    public List<Proiezione> getAll() {
        return repository.findAll();
    }

    public Proiezione getById(Long id) {

        Optional<Proiezione> proiezione = repository.findById(id);

        if(!proiezione.isPresent())
            throw new NotFoundException("Proiezione not available");

        return proiezione.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
