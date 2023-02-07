package com.example.carisiodigiampietro.controller;

import com.example.carisiodigiampietro.entity.Film;
import com.example.carisiodigiampietro.entity.Proiezione;
import com.example.carisiodigiampietro.entity.Sala;
import com.example.carisiodigiampietro.services.FilmService;
import com.example.carisiodigiampietro.services.ProiezioneService;
import com.example.carisiodigiampietro.services.SalaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/proiezioni")
@CrossOrigin("http://localhost:4200")
public class ProiezioneController {

    private final Logger logger = LoggerFactory.getLogger(ProiezioneController.class);

    @Autowired
    private ProiezioneService proiezioneService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private SalaService salaService;

    @PostMapping
    public Proiezione saveProiezione(
    @RequestParam("filmId") Long filmId,
    @RequestParam("salaId") Long salaId,
    @RequestParam("data") String data
    ) {
        Film film = filmService.getById(filmId);
        Sala sala = salaService.getById(salaId);

        Proiezione proiezione = Proiezione.proiezioneBuilder()
                .dataProiezione(stringToDate(data))
                .minDurata(film.getMinDurata())
                .anno(film.getAnno())
                .name(film.getNome())
                .regista(film.getRegista())
                .sala(sala)
                .build();
        logger.info("data formatter work "+stringToDate(data));
        logger.info("Save Proiezione in ProiezioneController");
        return proiezioneService.save(proiezione);
    }

    @GetMapping
    public List<Proiezione> getProiezioneList() {
        return proiezioneService.getAll();
    }

    @GetMapping("/{id}")
    public Proiezione getProiezioneById(@PathVariable("id") Long id) {
        return proiezioneService.getById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteProiezioneById(@PathVariable("id") Long id) {
        proiezioneService.deleteById(id);
        return "Proiezione deleted successfully";
    }

    @PutMapping("/{id}")
    public Proiezione updateProiezione(
            @PathVariable("id") Long id,
            @RequestBody Proiezione updateProiezione
    ) {

        Proiezione proiezione = proiezioneService.getById(id);

        if(updateProiezione.getDataProiezione() != null)
            proiezione.setDataProiezione(updateProiezione.getDataProiezione());
        if(updateProiezione.getDataFineProiezione() != null)
            proiezione.setDataFineProiezione(updateProiezione.getDataFineProiezione());
        if(updateProiezione.getNome()!=null)
            proiezione.setNome(updateProiezione.getNome());
        if(updateProiezione.getAnno()!=null)
            proiezione.setAnno(updateProiezione.getAnno());
        if(updateProiezione.getRegista()!=null)
            proiezione.setRegista(updateProiezione.getRegista());

        proiezioneService.save(proiezione);
        return proiezione;
    }

    private LocalDateTime stringToDate(String data){
        //metodo per convertire string in LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return LocalDateTime.parse(data, formatter);
    }

    //TODO metodo per aggiungere in programmazione film

}
