package org.indra.services;

import org.indra.models.*;
import org.indra.repository.*;

public class PeliculaService implements IPeliculaService {
	
	IPeliculaRepository repository;
	
	public PeliculaService(IPeliculaRepository repository) {
		//this.repository = new PeliculaMemoryRepository(); << Quiero que esta relacion no quede fija
		this.repository = repository;
	}

	private void crearBean() {
		System.out.println("Bean Creado");
	}

	private void destruirBean() {
		System.out.println("Bean Destruido");
	}

	public void add(Pelicula pelicula) {
        this.repository.add(pelicula);
    }
    
    public Pelicula findById(int id) {
        return this.repository.findById(id);
    }
}
