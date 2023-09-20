package org.indra.repository;

import java.util.List;
import org.indra.models.Pelicula;

public interface IPeliculaRepository {
    public List<Pelicula> findAll() throws Exception;
    public Pelicula findById(int id) throws Exception;
    public Pelicula findByName(String name) throws Exception;
    public void add(Pelicula objeto) throws Exception;
    public void delete(int id) throws Exception;
    public void update(Pelicula objeto) throws Exception;
}


