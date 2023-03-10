package com.example.carisiodigiampietro.controller;

import com.example.carisiodigiampietro.entity.Cinema;
import com.example.carisiodigiampietro.entity.Film;
import com.example.carisiodigiampietro.services.CinemaService;
import com.example.carisiodigiampietro.services.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/film")
@CrossOrigin("http://localhost:4200")
public class FilmController {

    private final Logger logger = LoggerFactory.getLogger(FilmController.class);

    @Autowired
    private FilmService filmService;

    @Autowired
    private CinemaService cinemaService;

    @PostMapping
    public Film saveFilm(
            @RequestParam("name") String nome,
            @RequestParam("regista") String regista,
            @RequestParam("anno") Integer anno,
            @RequestParam("minDurata") Integer minDurata,
            @RequestParam("cinemaId") Long id
    ) {

        Cinema cinema =  cinemaService.getById(id);

        Film film = Film.filmBuilder()
                .nome(nome)
                .regista(regista)
                .anno(anno)
                .minDurata(minDurata)
                .build();

        filmService.save(film);

        cinema.addFilmToCatalogo(film);

        cinemaService.save(cinema);

        logger.info("Save Film in FilmController");
        return film;
    }

    @GetMapping
    public List<Film> getFilmList() {
        return filmService.getAll();
    }

    @GetMapping("{id}")
    public Film getFilmById(@PathVariable("id") Long id) {
        return filmService.getById(id);
    }

    @DeleteMapping("{id}")
    public Film deleteFilmById(@PathVariable("id") Long id) {
        Film film = filmService.getById(id);
        logger.info("Film deleted successfully");
        filmService.deleteById(id);
        return film;
    }

    @PutMapping("{id}")
    public Film updateFilm(
            @PathVariable("id") Long id,
            @RequestBody Film updateFilm
    ) {

        Film film = filmService.getById(id);

        if(updateFilm.getNome()!=null)
            film.setNome(updateFilm.getNome());
        if(updateFilm.getAnno()!=null)
            film.setAnno(updateFilm.getAnno());
        if(updateFilm.getRegista()!=null)
            film.setRegista(updateFilm.getRegista());


        filmService.save(film);
        return film;
    }



}