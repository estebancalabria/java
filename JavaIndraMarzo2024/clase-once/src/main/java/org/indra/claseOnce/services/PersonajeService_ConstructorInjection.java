package org.indra.claseOnce.services;

import org.indra.claseOnce.models.Personaje;
import org.indra.claseOnce.persistence.Repository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersonajeService_ConstructorInjection implements PersonajeServiceInterface {
	
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
