package com.example.carisiodigiampietro.controller;

import com.example.carisiodigiampietro.entity.Cinema;
import com.example.carisiodigiampietro.entity.Proiezione;
import com.example.carisiodigiampietro.entity.Sala;
import com.example.carisiodigiampietro.services.CinemaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/cinema")
public class CinemaController {

    private final Logger logger = LoggerFactory.getLogger(CinemaController.class);

    @Autowired
    private CinemaService cinemaService;

    @PostMapping
    public Cinema saveCinema(
            @RequestParam("name") String name
    ) {
        Cinema cinema = Cinema.builder().name(name).build();

        logger.info("Save Cinema in CinemaController");
        return cinemaService.save(cinema);
    }

    @GetMapping
    public List<Cinema> getCinemaList() {
        return cinemaService.getAll();
    }

    @GetMapping("{id}")
    public Cinema getCinemaById(@PathVariable("id") Long id) {
        return cinemaService.getById(id);
    }

    @GetMapping("/storico/{id}")
    public List<Proiezione> getStoricoProiezioni(@PathVariable("id") Long id){
        Cinema cinema = cinemaService.getById(id);
        List<Proiezione> proiezioniPassate = new ArrayList<Proiezione>();
        Set<Sala> saleCinema = cinema.getSale();

        for(Sala s : saleCinema){//per ciascuna sala del cinema
            List<Proiezione> listP = s.getInProgrammazione();

            for (Proiezione proiezione : listP){//per ciascuna proiezione nella sala
                if(proiezione.getDataProiezione().isBefore(LocalDateTime.now()))
                    proiezioniPassate.add(proiezione);
            }

        }

        return proiezioniPassate;
    }

    @GetMapping("/inProgrammazione/{id}")
    public List<Proiezione> getFutureProiezioni(@PathVariable("id") Long id){
        Cinema cinema = cinemaService.getById(id);
        List<Proiezione> proiezioniPassate = new ArrayList<Proiezione>();
        Set<Sala> saleCinema = cinema.getSale();

        for(Sala s : saleCinema){
            List<Proiezione> listP = s.getInProgrammazione();

            for (Proiezione proiezione : listP){
                if(proiezione.getDataProiezione().isAfter(LocalDateTime.now()))
                    proiezioniPassate.add(proiezione);
            }

        }

        return proiezioniPassate;
    }

    //TODO permettere di cercare tramite data di inizio e di fine della proiezione

    @GetMapping("/byDataInizio/{id}")
    public List<Proiezione> getProiezioniByDataInizio(
            @PathVariable("id") Long id,
            @RequestParam("dataInizio") String dataInizio
    ){
        Cinema cinema = cinemaService.getById(id);
        List<Proiezione> proiezioniPassate = new ArrayList<Proiezione>();
        Set<Sala> saleCinema = cinema.getSale();

        for(Sala s : saleCinema){
            List<Proiezione> listP = s.getInProgrammazione();

            for (Proiezione proiezione : listP){
                if(proiezione.getDataProiezione().isEqual(stringToDate(dataInizio)))
                    proiezioniPassate.add(proiezione);
            }

        }

        return proiezioniPassate;
    }

    @GetMapping("/byDataFine/{id}")
    public List<Proiezione> getProiezioniByDataFine(
            @PathVariable("id") Long id,
            @RequestParam("dataFine") String dataFine
    ){
        Cinema cinema = cinemaService.getById(id);
        List<Proiezione> proiezioniPassate = new ArrayList<Proiezione>();
        Set<Sala> saleCinema = cinema.getSale();

        for(Sala s : saleCinema){
            List<Proiezione> listP = s.getInProgrammazione();

            for (Proiezione proiezione : listP){
                if(proiezione.getDataFineProiezione().isEqual(stringToDate(dataFine)))
                    proiezioniPassate.add(proiezione);
            }

        }

        return proiezioniPassate;
    }


    @DeleteMapping("{id}")
    public String deleteCinemaById(@PathVariable("id") Long id) {
        String nomeCinema = cinemaService.getById(id).getName();
        cinemaService.deleteById(id);
        return "Cinema "+ nomeCinema +" deleted successfully";
    }

    @PutMapping("{id}")
    public Cinema updateCinema(
            @PathVariable("id") Long id,
            @RequestBody Cinema updateCinema
    ) {

        Cinema cinema = cinemaService.getById(id);

        if(updateCinema.getName()!=null){
            cinema.setName(updateCinema.getName());
        }

        cinemaService.save(cinema);
        return cinema;
    }

    private LocalDateTime stringToDate(String data){
        //metodo per convertire string in LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return LocalDateTime.parse(data, formatter);
    }

}