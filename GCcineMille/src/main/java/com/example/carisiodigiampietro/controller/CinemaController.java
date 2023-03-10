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
@CrossOrigin("http://localhost:4200")
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

    @GetMapping("/byRange/{id}")
    public List<Proiezione> getProiezioniInRange(
            @PathVariable("id") Long id,
            @RequestParam("dataFrom") String dataFrom,
            @RequestParam("dataTo") String dataTo
    ){
        Cinema cinema = cinemaService.getById(id);
        List<Proiezione> proiezioniInRange = new ArrayList<Proiezione>();
        Set<Sala> saleCinema = cinema.getSale();

        for(Sala s : saleCinema){
            List<Proiezione> listP = s.getInProgrammazione();

            for (Proiezione proiezione : listP){
                if(proiezione.getDataProiezione().isAfter(stringToDate(dataFrom)) && proiezione.getDataProiezione().isBefore(stringToDate(dataTo)))
                    proiezioniInRange.add(proiezione);
            }

        }

        return proiezioniInRange;
    }


    @DeleteMapping("{id}")
    public Cinema deleteCinemaById(@PathVariable("id") Long id) {
        Cinema cinema = cinemaService.getById(id);
        cinemaService.deleteById(id);
        logger.info("Cinema "+ cinema.getName() +" deleted successfully");
        return cinema;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return LocalDateTime.parse(data, formatter);
    }

}