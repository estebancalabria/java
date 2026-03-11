package org.indra.claseOnce.services;

import org.indra.claseOnce.logging.Logger;
import org.indra.claseOnce.models.*;
import org.indra.claseOnce.persistence.Repository;

import lombok.*;

public class PersonajeService implements PersonajeServiceInterface {
	
	@Setter @Getter
	private Repository<Personaje> repository;
	
	//@Setter
	//private Logger logger = null; //Como defino que logger voy a usar?

	public void registrarPersonaje(String nombre) {
		
		//Incorporamos el patron proxy
		//En vez de escribir los logs antes y despues coy a hacer un prixy
		//logger.log("PersonaService : Antes del regitrar");
		
		if ((nombre==null) || (nombre.trim().length()==0)) {
			throw new Error("Nombre del personaje vacio");
		}
		
		Personaje p = new Personaje();
		p.setNombre(nombre);
		
		repository.save(p);
		
		//Incorporamos el patron proxy
		//En vez de escribir los logs antes y despues coy a hacer un prixy
		//logger.log("PersonaService : Despues del regitrar");
	}
	
	public Personaje recuperar(int id) {
		//Incorporamos el patron proxy
		//En vez de escribir los logs antes y despues coy a hacer un prixy
		//logger.log("PersonaService:  Antes de Recuperar ");
        return this.repository.findById(id);

	}
}
