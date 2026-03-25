package com.example.jpa_h2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.jpa_h2.entities.Cancion;
import com.example.jpa_h2.repositories.CancionRepository;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    @Autowired
    private CancionRepository repository;

    @PostMapping
    public Cancion crear(@RequestBody Cancion cancion) {
        return repository.save(cancion);
    }

    @GetMapping
    public List<Cancion> listar() {
        return repository.findAll();
    }
}