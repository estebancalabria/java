package org.indra.repository;

import org.indra.models.Pelicula;

public class PeliculaDatabaseRepository implements IPeliculaRepository {

	Pelicula p;
	@Override
	public void add(Pelicula pelicula) {
		System.out.println("Agrego en la base de datos");
		this.p=pelicula;
	}

	@Override
	public Pelicula findById(int id) {
		System.out.println("Recupero de la base de datos.");
		return p;
	}

}
