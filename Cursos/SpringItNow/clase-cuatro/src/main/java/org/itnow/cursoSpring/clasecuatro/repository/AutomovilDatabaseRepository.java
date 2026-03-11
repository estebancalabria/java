package org.itnow.cursoSpring.clasecuatro.repository;

import java.util.*;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.itnow.cursoSpring.clasecuatro.models.Automovil;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Repository;

@Repository
@Profile("Produccion")
public class AutomovilDatabaseRepository implements AutomovilRepository {

	private Log log = LogFactory.getLog(AutomovilDatabaseRepository.class);
	
	@Autowired
	private AutomovilJpaRepository repository;

	public AutomovilDatabaseRepository() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insert(Automovil automovil) {
		log.info("INSERT DB : FALTA IMPLEMENTACION");
		this.repository.save(automovil);
	}

	@Override
	public void update(Automovil automovil) {
		log.info("UPDATE DB : FALTA IMPLEMENTACION");
		this.repository.save(automovil);
	}

	@Override
	public void delete(int id) {
		log.info("DELETE DB : FALTA IMPLEMENTACION");
		this.repository.deleteById(id);
	}

	@Override
	public List<Automovil> findAll() {
		log.info("FIND ALL DB : FALTA IMPLEMENTACION");
		return this.repository.findAll();
	}

	@Override
	public Automovil findById(int id) {
		log.info("FIND DB : FALTA IMPLEMENTACION");
		return this.repository.findById(id).orElse(null);
	}
}
