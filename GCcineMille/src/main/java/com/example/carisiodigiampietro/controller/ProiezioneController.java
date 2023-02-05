package com.example.carisiodigiampietro.controller;

import com.example.carisiodigiampietro.entity.Proiezione;
import com.example.carisiodigiampietro.services.ProiezioneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proiezioni")
public class ProiezioneController {

    private final Logger logger = LoggerFactory.getLogger(ProiezioneController.class);

    @Autowired
    private ProiezioneService proiezioneService;

    @PostMapping
    public Proiezione saveProiezione(
//          TODO gestire il post
//            @Valid
//            @RequestParam("name") String name,
//            @RequestParam(value="address",required=false) String address,
    ) {
        Proiezione proiezione = Proiezione.proiezioneBuilder().build();

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
            @PathVariable("id") Long id
//            @RequestParam("name") String name
    ) {

        Proiezione proiezione = proiezioneService.getById(id);

        //TODO gestire il put

        proiezioneService.save(proiezione);
        return proiezione;
    }

}
