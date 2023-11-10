package org.itnow.cursoSpring.clasecuatro.services;

import java.util.*;


import org.apache.juli.logging.*;
import org.itnow.cursoSpring.clasecuatro.models.*;
import org.itnow.cursoSpring.clasecuatro.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class AutomovilService implements CrudService<Automovil> {

	private Log log = LogFactory.getLog(AutomovilService.class);
	
	@Autowired
	private AutomovilRepository repository;

	@Override
	public void registrarNuevo(Automovil nuevo) throws CrudException {
		log.info("Registrando Automovil Nuevo");
		
		if ((nuevo.getModelo()== null) || (nuevo.getModelo().trim().length()==0)) {
			throw new CrudException("VALIDACION : El modelo del automovil es requerido" );
		}
		
		this.repository.insert(nuevo);
		
	}

	@Override
	public void actualizarInformacion(Automovil existente) {
		log.info("Registrando Actualizando Informacion Aitomovil");
		this.repository.update(existente);
	}

	@Override
	public void darDeBaja(int id) {
		log.info("Dando de baja automovil");
		this.repository.delete(id);
	}

	@Override
	public List<Automovil> obtenerTodos() {
		log.info("Obeniendo todos los automoviles");
		/*List<Automovil> misAutosFavoritos = new ArrayList<>();
		misAutosFavoritos.add(new Automovil("Seat","Ibiza",2000));
		misAutosFavoritos.add(new Automovil("Porsche","911",1960));
		misAutosFavoritos.add(new Automovil("Bugatti","Veyron",2015));
		misAutosFavoritos.add(new Automovil("Ford","Mustang",1967));
		return misAutosFavoritos;*/
		
		return this.repository.findAll();
	}

	@Override
	public Automovil obtenerPorId(int id) {
		log.info("Obeniendo un automovil con id" + id);
		//Automovil result = new Automovil("Seat","Ibiza",2000);
		//result.setId(id);
		//return result;
		return this.repository.findById(id);
	}
}
