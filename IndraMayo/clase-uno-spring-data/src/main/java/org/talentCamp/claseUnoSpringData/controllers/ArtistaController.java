package org.talentCamp.claseUnoSpringData.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.talentCamp.claseUnoSpringData.dto.ArtistaDTO;
import org.talentCamp.claseUnoSpringData.models.Artista;
import org.talentCamp.claseUnoSpringData.services.ArtistaService;

import java.util.List;

@RestController
public class ArtistaController {

    @Autowired
    private ArtistaService service;

    //Ojo tengo que crear el artista DTO
    @GetMapping(value = "/api/artista/conD")
    public ResponseEntity<List<ArtistaDTO>> conD(){
        return ResponseEntity.ok(this.service.recuperarEmpiezanConD());
    }

    @GetMapping(value = "/api/artista", params = "comienzaCon")
    public ResponseEntity<List<ArtistaDTO>> conD(@RequestParam String comienzaCon){
        System.out.println("Comienza Con " + comienzaCon);
        return ResponseEntity.ok(this.service.recuperarEmpiezanCon(comienzaCon));
    }
}
