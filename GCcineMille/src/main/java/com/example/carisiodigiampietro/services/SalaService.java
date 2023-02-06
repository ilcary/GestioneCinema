package com.example.carisiodigiampietro.services;

import com.example.carisiodigiampietro.entity.Sala;
import com.example.carisiodigiampietro.exception.NotFoundException;
import com.example.carisiodigiampietro.repository.SalaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public List<Sala> getAllByCinemaId(Long id) {
        return repository.findSalaByCinemaId(id);
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
