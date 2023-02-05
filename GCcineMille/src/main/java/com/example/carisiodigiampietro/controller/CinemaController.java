package com.example.carisiodigiampietro.controller;

import com.example.carisiodigiampietro.entity.Cinema;
import com.example.carisiodigiampietro.services.CinemaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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

}