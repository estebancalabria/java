package org.talentCamp.claseDos.services;

import jakarta.validation.Valid;
import org.talentCamp.claseDos.models.Pelicula;

import java.util.List;

public interface PeliculaService {
    List<Pelicula> obtenerTodas();
    Pelicula obtenerPorId(int id) throws NotFoundServiceException;
    void registrarPelicula(Pelicula pelicula);
    void borrarPelicula(int id)  throws NotFoundServiceException;
    void actualizarPelicula(Pelicula pelicula) throws NotFoundServiceException;
}
