package com.example.jpa_h2.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa_h2.entities.Cancion;

public interface CancionRepository extends JpaRepository<Cancion, Long> {

}