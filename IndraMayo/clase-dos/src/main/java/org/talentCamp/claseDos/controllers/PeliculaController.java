package org.talentCamp.claseDos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.*;
import org.talentCamp.claseDos.models.Pelicula;
import org.talentCamp.claseDos.services.PeliculaService;

import java.util.List;

@RestController
public class PeliculaController {

    @Autowired  //Inyeccion de dependencia por atributo / setter
    public PeliculaService service;

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/api/pelicula")
    public Pelicula add(@RequestBody() Pelicula pelicula){
        System.out.println(pelicula.getNombre());
        if ((pelicula.getNombre()==null) || (pelicula.getNombre().trim().length()==0)){
            throw new Error("Falta el nombre");
        }

        this.service.registrarPelicula(pelicula);
        System.out.println(pelicula.getId());
        return pelicula;
    }

    @GetMapping("/api/pelicula")
    @CrossOrigin("http://localhost:4200")
    public List<Pelicula> todas(){
        return this.service.obtenerTodas();
    }
}
