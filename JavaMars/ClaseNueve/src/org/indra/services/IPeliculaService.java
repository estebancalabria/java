package org.indra.services;

import org.indra.models.*;

public interface IPeliculaService {
	void add(Pelicula pelicula);
	Pelicula findById(int id);
}
