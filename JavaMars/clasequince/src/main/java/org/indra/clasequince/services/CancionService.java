package org.indra.clasequince.services;

import org.indra.clasequince.models.*;
import org.springframework.stereotype.*;

@Service
public class CancionService implements ICancionService {

	@Override
	public void add(Cancion cancion) throws ValidationException {
		
		//Simuglo que la base de datos me genero un id
		cancion.setId(1);

		if (cancion.getNombre().length()<3) {
			throw new ValidationException("Nombre muy corto");
		}
		
		//Al final grabo en la base de datos
	}

}
