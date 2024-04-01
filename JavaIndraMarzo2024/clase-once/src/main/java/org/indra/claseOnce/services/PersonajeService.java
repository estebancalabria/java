package org.indra.claseOnce.services;

import org.indra.claseOnce.models.*;
import org.indra.claseOnce.persistence.Repository;

import lombok.*;

public class PersonajeService implements PersonajeServiceInterface {
	
	@Setter @Getter
	private Repository<Personaje> repository;

	public void registrarPersonaje(String nombre) {
		
		if ((nombre==null) || (nombre.trim().length()==0)) {
			throw new Error("Nombre del personaje vacio");
		}
		
		Personaje p = new Personaje();
		p.setNombre(nombre);
		
		repository.save(p);
	}
	
	public Personaje recuperar(int id) {
        return this.repository.findById(id);

	}
}
