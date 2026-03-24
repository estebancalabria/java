package org.gobvasco.cursomsa.claseuno.services;

import java.util.List;

import org.gobvasco.cursomsa.claseuno.dto.Persona;

public interface IPersonaService {
	List<Persona> getAll();
	
	Persona getById(int id);
	
	void add(Persona p);
}
