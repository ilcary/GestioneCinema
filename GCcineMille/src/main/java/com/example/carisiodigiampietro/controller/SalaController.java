package com.example.carisiodigiampietro.controller;

import com.example.carisiodigiampietro.entity.Cinema;
import com.example.carisiodigiampietro.entity.Sala;
import com.example.carisiodigiampietro.services.CinemaService;
import com.example.carisiodigiampietro.services.SalaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sala")
@CrossOrigin("http://localhost:4200")
public class SalaController {

    private final Logger logger = LoggerFactory.getLogger(SalaController.class);

    @Autowired
    private SalaService salaService;

    @Autowired
    private CinemaService cinemaService;

    @PostMapping
    public Sala saveSala(
            @RequestParam("tecnologiaIMAX") boolean tecnologiaIMAX,
            @RequestParam("posti") int posti,
            @RequestParam("cinemaId") Long cinemaId
    ) {

        Cinema cinema = cinemaService.getById(cinemaId);

        //creo la sala
        Sala sala = Sala.builder()
                .tecnologiaIMAX(tecnologiaIMAX)
                .posti(posti)
                .cinema(cinema)
                .build();
        //la salvo con già il cinema asegnato
        salaService.save(sala);
        //aggiungo la sala al cinema
        cinema.addSalaToCinema(sala);
        //aggiorno le modifiche anche al cinema
        cinemaService.save(cinema);

        logger.info("Save Sala in SalaController");
        return sala;
    }

    @GetMapping("allSalaByCinemaId/{id}")
    public List<Sala> getAllSalaByCinemaId(@PathVariable("id") Long id) {
        if(salaService.getAllByCinemaId(id).size() != 0){
            return salaService.getAllByCinemaId(id);
        }
        logger.error("Non sono state trovate sale per il cinemaId fornito");
        return null;
    }

    @GetMapping("{id}")
    public Sala getSalaById(@PathVariable("id") Long id) {
        return salaService.getById(id);
    }

    @DeleteMapping("{id}")
    public Sala deleteSalaById(@PathVariable("id") Long id) {
        //mi prendo la sala da eliminare
        Sala sala = salaService.getById(id);
        //la elimino dalla lista sale del cinema appartenente
        cinemaService.getById(sala.getCinema().getId()).removeSalaFromCinema(sala);
        //elimino definitivamente la sala
        salaService.deleteById(id);
        logger.info("Sala deleted successfully");
        return sala;
    }

    @PutMapping("{id}")
    public Sala updateSala(
            @PathVariable("id") Long id,
            @RequestBody Sala updateSala
    ) {

        System.out.println(updateSala);

        Sala sala = salaService.getById(id);

        if(updateSala.getPosti() != null){
            sala.setPosti(updateSala.getPosti());
        }
        if(updateSala.getTecnologiaIMAX() != null){
            sala.setTecnologiaIMAX(updateSala.getTecnologiaIMAX());
        }

        salaService.save(sala);
        return sala;
    }

}