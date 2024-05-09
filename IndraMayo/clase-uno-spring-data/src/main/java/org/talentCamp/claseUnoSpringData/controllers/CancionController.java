package org.talentCamp.claseUnoSpringData.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.talentCamp.claseUnoSpringData.models.Cancion;
import org.talentCamp.claseUnoSpringData.services.CancionService;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CancionController {

    @Autowired
    private CancionService service;

    //Deberia ser un Post pero hago un get para probarlo desde el navegador
    @GetMapping("/api/cancion/test")
    public ResponseEntity<Object> test(){

        Cancion aveMaria = Cancion.builder()
                .titulo("Ave Naria")
                .artista("David Bisbal")
                .fecha(LocalDate.of(2002,3,1))
                .duracionSegundos(120)
                .build();

        Cancion fiestaPagana = Cancion.builder()
                .titulo("Fiesta Pagana")
                .artista("Mago de Oz")
                .fecha(LocalDate.of(2005,3,1))
                .duracionSegundos(240)
                .build();

        Cancion unaCalleEnParis = Cancion.builder()
                .titulo("Una Calle en Paris")
                .artista("Duncan dhu")
                .fecha(LocalDate.of(1988,3,1))
                .duracionSegundos(340)
                .build();

        Cancion thuderstruck = Cancion.builder()
                .titulo("Thuderstruck")
                .artista("ACDC")
                .fecha(LocalDate.of(1995,3,1))
                .duracionSegundos(240)
                .build();

        if ((long) this.service.recuperarTodos().size() == 0) {
            this.service.registrarNuevo(aveMaria);
            this.service.registrarNuevo(fiestaPagana);
            this.service.registrarNuevo(unaCalleEnParis);
            this.service.registrarNuevo(thuderstruck);
        }


        return ResponseEntity.ok().body("Canciones agregdas");
    }

    @GetMapping("/api/cancion")
    public ResponseEntity<List<Cancion>> getTodas(){
        return ResponseEntity.ok().body(this.service.recuperarTodos());
    }

    @GetMapping("/api/cancion/{id}")
    public ResponseEntity<Cancion> getCancion(@PathVariable int id){
        return ResponseEntity.ok().body(this.service.recuperarPorId(id));
    }

    @PostMapping("/api/cancion")
    public ResponseEntity<Cancion> agregarCancion(@RequestBody Cancion cancion){
       return ResponseEntity.ok().body(this.service.registrarNuevo(cancion));
    }

    @PutMapping("/api/cancion")
    public ResponseEntity<Cancion> actualizarCancion(@RequestBody Cancion cancion){
        return ResponseEntity.ok().body(this.service.actualizar(cancion));
    }

    @DeleteMapping("/api/cancion/{id}")
    public ResponseEntity<Object> borrarCancion(@PathVariable int id) {
        this.service.borrar(id);
        return ResponseEntity.ok().build();
    }
}


