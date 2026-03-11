package org.indra.repository;

import java.util.*;

import org.indra.models.Pelicula;

public class PeliculaMemoryRepository implements IPeliculaRepository {

	List<Pelicula> repository = new ArrayList<Pelicula>();
    @Override
    
    public void add(Pelicula pelicula) {
        this.repository.add(pelicula);
        pelicula.setId( repository.stream().mapToInt(p->p.getId()).max().getAsInt() + 1);
    }
    @Override
    public Pelicula findById(int id) {
        return (Pelicula) this.repository.stream().filter(p -> p.getId() == id).findFirst().get();
    }

}
