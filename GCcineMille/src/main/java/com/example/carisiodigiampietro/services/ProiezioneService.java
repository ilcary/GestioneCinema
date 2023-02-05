package com.example.carisiodigiampietro.services;

import com.example.carisiodigiampietro.entity.Proiezione;
import com.example.carisiodigiampietro.exception.NotFoundException;
import com.example.carisiodigiampietro.repository.ProiezioneRepo;
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