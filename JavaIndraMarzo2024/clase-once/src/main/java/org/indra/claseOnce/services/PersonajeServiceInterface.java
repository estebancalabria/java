package org.indra.claseOnce.services;

import org.indra.claseOnce.models.Personaje;

public interface PersonajeServiceInterface {
	 void registrarPersonaje(String nombre);
	 Personaje recuperar(int id);

}
