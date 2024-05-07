package org.talentCamp.claseDos.services;

import org.talentCamp.claseDos.models.Pelicula;

import java.util.List;

public interface PeliculaService {
    List<Pelicula> obtenerTodas();
    Pelicula obtenerPorId(int id);
    void registrarPelicula(Pelicula pelicula);
    void borrarPelicula(int id);
    void actualizarPelicula(Pelicula pelicula);
}
