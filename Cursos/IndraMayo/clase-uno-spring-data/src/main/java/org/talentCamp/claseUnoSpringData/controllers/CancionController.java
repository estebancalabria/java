package org.talentCamp.claseUnoSpringData.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.talentCamp.claseUnoSpringData.dto.CancionDTO;
import org.talentCamp.claseUnoSpringData.dto.CancionMapper;
import org.talentCamp.claseUnoSpringData.models.Cancion;
import org.talentCamp.claseUnoSpringData.repositories.CancionRepository;
import org.talentCamp.claseUnoSpringData.services.CancionService;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

@RestController
public class CancionController {

    @Autowired
    private CancionService service;

    @GetMapping("/api/cancion/testMapper")
    public ResponseEntity<Object> testMapper() {
        CancionDTO aveMaria =
                CancionDTO
                        .builder()
                        .id(12)
                        .nombre("Ave Naria")
                        .compositor("David Bisbal")
                        .año(LocalDate.now())
                        .duracion(120).build();


        System.out.println("------------");
        System.out.println(aveMaria);

        Cancion convertida = CancionMapper.INSTANCE.fromDto(aveMaria);
        System.out.println("------------");
        System.out.println(convertida);

        return ResponseEntity.ok("Mira la consola a er si funciono");
    }


        //Deberia ser un Post pero hago un get para probarlo desde el navegador
    @GetMapping("/api/cancion/test")
    public ResponseEntity<Object> test() {

        CancionDTO aveMaria = CancionDTO.builder()
                .nombre("Ave Naria")
                .compositor("David Bisbal")
                .año(LocalDate.of(20002, Month.JANUARY,1))
                .duracion(120)
                .build();

        CancionDTO fiestaPagana = CancionDTO.builder()
                .nombre("Fiesta Pagana")
                .compositor("Mago de Oz")
                .año(LocalDate.of(2010, Month.JANUARY, 1))
                .duracion(240)
                .build();

        CancionDTO unaCalleEnParis = CancionDTO.builder()
                .nombre("Una Calle en Paris")
                .compositor("Duncan dhu")
                .año(LocalDate.of(1987, Month.JANUARY,1))
                .duracion(340)
                .build();

        if ((long) this.service.recuperarTodos().size() == 0) {
            this.service.registrarNuevo(aveMaria);
            this.service.registrarNuevo(fiestaPagana);
            this.service.registrarNuevo(unaCalleEnParis);
        }

        return ResponseEntity.ok().body("Canciones agregdas");
    }

    @GetMapping("/api/cancion")
    public ResponseEntity<List<CancionDTO>> getTodas() {
        return ResponseEntity.ok().body(this.service.recuperarTodos());
    }

    @GetMapping(value = "/api/cancion", params = "hastaDuracion")
    //@GetMapping("/api/cancion/cortas")
    public ResponseEntity<List<CancionDTO>> getCortas(@RequestParam("hastaDuracion") int max) {
        //return ResponseEntity.ok().body(this.service.recuperarCancionesMenosDe(340));
        return ResponseEntity.ok().body(this.service.recuperarCancionesMenosDe(max));
    }

    @GetMapping("/api/cancion/{id}")
    public ResponseEntity<CancionDTO> getCancion(@PathVariable int id) {
        return ResponseEntity.ok().body(this.service.recuperarPorId(id));
    }

    @PostMapping("/api/cancion/{id}/puntuar/{nota}")
    public ResponseEntity<Object> puntuarCancion(@PathVariable int id,
                                                     @Min(1) @Max(10) @PathVariable int nota) {
        this.service.puntuarCancion(id, nota);
        return ResponseEntity.ok().body(Map.of("resultado", "Puntuacion Recibida. Gracias por puntuar."));
    }

    @PostMapping("/api/cancion")
    public ResponseEntity<CancionDTO> agregarCancion(@RequestBody CancionDTO cancion) {
        return ResponseEntity.ok().body(this.service.registrarNuevo(cancion));
    }

    @PutMapping("/api/cancion")
    public ResponseEntity<CancionDTO> actualizarCancion(@RequestBody @Valid CancionDTO cancion) {
        return ResponseEntity.ok().body(this.service.actualizar(cancion));
    }

    @DeleteMapping("/api/cancion/{id}")
    public ResponseEntity<Object> borrarCancion(@PathVariable int id) {
        this.service.borrar(id);
        return ResponseEntity.ok().build();
    }
}


