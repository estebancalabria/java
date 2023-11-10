package org.itnow.cursoSpring.clasecuatro.repository;

import java.util.*;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.itnow.cursoSpring.clasecuatro.models.Automovil;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Repository;

@Repository
@Profile("Produccion")
public class AutomovilDatabaseRepository implements AutomovilRepository {

	private Log log = LogFactory.getLog(AutomovilDatabaseRepository.class);

	public AutomovilDatabaseRepository() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Automovil automovil) {
		log.info("INSERT DB : FALTA IMPLEMENTACION");
		
	}

	@Override
	public void update(Automovil automovil) {
		log.info("UPDATE DB : FALTA IMPLEMENTACION");
		
	}

	@Override
	public void delete(int id) {
		log.info("DELETE DB : FALTA IMPLEMENTACION");
		
	}

	@Override
	public List<Automovil> findAll() {
		log.info("FIND ALL DB : FALTA IMPLEMENTACION");
		return new ArrayList<>();
	}

	@Override
	public Automovil findById(int id) {
		log.info("FIND DB : FALTA IMPLEMENTACION");
		return null;
	}

}
