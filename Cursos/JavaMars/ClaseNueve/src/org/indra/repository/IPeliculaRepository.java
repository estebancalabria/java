package org.indra.repository;

import org.indra.models.Pelicula;

public interface IPeliculaRepository {
	void add(Pelicula pelicula);
	Pelicula findById(int id);
}
