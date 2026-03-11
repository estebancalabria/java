package org.indra.clasequince.services;

import org.indra.clasequince.models.*;

public interface ICancionService {

	void add(Cancion cancion) throws ValidationException;
	//... el resto de los metodos del servicio
}
