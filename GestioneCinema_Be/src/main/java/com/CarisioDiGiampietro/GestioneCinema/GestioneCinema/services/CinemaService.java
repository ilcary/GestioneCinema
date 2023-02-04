package com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.services;

import com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.exception.NotFoundException;
import com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.models.Cinema;
import com.CarisioDiGiampietro.GestioneCinema.GestioneCinema.repo.CinemaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {
    
    @Autowired
    private CinemaRepo repository;
    
    public Cinema save(Cinema x){
        return  repository.save(x);
    }
    public List<Cinema> getAll() {
        return repository.findAll();
    }

    public Cinema getById(Long id) {

        Optional<Cinema> address = repository.findById(id);

        if(!address.isPresent())
            throw new NotFoundException("Cinema not available");

        return address.get();
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
