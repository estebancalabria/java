package org.itnow.cursoSpring.clasecuatro.repository;

import java.util.*;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.itnow.cursoSpring.clasecuatro.models.Automovil;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository 
@Profile("Test")
public class AutomovilMockRepository implements AutomovilRepository {

	private List<Automovil> automoviles = new ArrayList<Automovil>();
	private Log log = LogFactory.getLog(AutomovilMockRepository.class);

	
	private static int ULTIMO_ID = 0;
	private static int nextId() {
		AutomovilMockRepository.ULTIMO_ID++;
		return AutomovilMockRepository.ULTIMO_ID; 
	}
	
	
	public AutomovilMockRepository() {
		Automovil auto = new Automovil("Seat","Ibiza",2000);
		auto.setId(AutomovilMockRepository.nextId());
		this.automoviles.add(auto);
		
		auto = new Automovil("Porsche","911",1960);
		auto.setId(AutomovilMockRepository.nextId());
		this.automoviles.add(auto);

		auto = new Automovil("Bugatti","Veyron",2015);
		auto.setId(AutomovilMockRepository.nextId());
		this.automoviles.add(auto);

		auto = new Automovil("Ford","Mustang",1967);
		auto.setId(AutomovilMockRepository.nextId());
		this.automoviles.add(auto);
		
		auto = new Automovil("Opel", "Astra", 2003);
		auto.setId(AutomovilMockRepository.nextId());
		this.automoviles.add(auto);
	}

	@Override
	public void insert(Automovil automovil) {
		log.info("INSERT");
		automovil.setId(AutomovilMockRepository.nextId());
		this.automoviles.add(automovil);
	}

	@Override
	public void update(Automovil automovil) {
		log.info("UPDATE");
		Automovil encontrado = this.automoviles.stream()
			.filter(a -> (a.getId()== automovil.getId()))
			.findFirst().orElse(null);
		
		/*Automovil enontrado = null;
		for (Automovil actual : automoviles) {
			if (actual.getId()==automovil.getId()) {
				encontrado = actual;
			}
		}*/
		
		if (encontrado!=null) {
			this.automoviles.remove(encontrado);
			this.automoviles.add(automovil);
		}
		
	}

	@Override
	public void delete(int id) {
		log.info("DELETE");
		Automovil encontrado = this.automoviles.stream()
				.filter(a -> (a.getId()== id))
				.findFirst().orElse(null);
			
		if (encontrado!=null) {
			this.automoviles.remove(encontrado);
		}		
	}


	@Override
	public List<Automovil> findAll() {
		log.info("FIND ALL");
		// TODO Auto-generated method stub
		return this.automoviles;
	}


	@Override
	public Automovil findById(int id) {
		log.info("FIND");
		// TODO Auto-generated method stub
		return this.automoviles.stream()
				.filter(a -> (a.getId()== id))
				.findFirst().orElse(null);
	}

}
