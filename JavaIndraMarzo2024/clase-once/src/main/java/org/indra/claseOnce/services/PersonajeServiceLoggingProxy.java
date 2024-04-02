package org.indra.claseOnce.services;

import org.indra.claseOnce.logging.Logger;
import org.indra.claseOnce.models.Personaje;

import lombok.Setter;

public class PersonajeServiceLoggingProxy implements PersonajeServiceInterface {

	@Setter
	private PersonajeServiceInterface objetoConcreto;
	@Setter

	private Logger logger = null; // Como defino que logger voy a usar?

	@Override
	public void registrarPersonaje(String nombre) {
		logger.log("PersonaService : Antes del regitrar");
		try {
			objetoConcreto.registrarPersonaje(nombre);
			logger.log("PersonaService : Despues del regitrar");
		} catch (Exception ex) {
			logger.log("Error Persona Service registrar :" + ex.getMessage());
			throw ex;
		}
	}

	@Override
	public Personaje recuperar(int id) {
		// TODO Auto-generated method stub
		logger.log("PersonaService : Antes del recuperar");
		try {
			Personaje p = this.objetoConcreto.recuperar(id);
			logger.log("PersonaService : Antes del recuperar");
			return p;			
		} catch (Exception ex) {
			logger.log("Error Persona Service recuperar:" + ex.getMessage());
			throw ex;
		}

	}

}
