package org.gobvasco.cursomsa.claseuno.services;

import java.util.ArrayList;
import java.util.List;

import org.gobvasco.cursomsa.claseuno.dto.Persona;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService {
	
	List<Persona> personas = new ArrayList<>();
	
	public PersonaService() {
		Persona juan = new Persona();
		juan.setDocumento(1);
		juan.setNombre("Juan");
		juan.setApellido("Perez");
		
		personas.add(juan);
		
		Persona maria = new Persona();
		maria.setDocumento(2);
		maria.setNombre("Maria");
		maria.setApellido("Gomez");
		
		personas.add(maria);	
	}

	public List<Persona> getAll(){
		return personas;
	}

	@Override
	public Persona getById(int id) {
		// TODO Auto-generated method stub
		if (id > this.personas.size()) {
			//Devolvemos nulo o lanzamos una excepcion
			return null;
		}
		return this.personas.get(id-1);
	}

	@Override
	public void add(Persona p) {
		// TODO Auto-generated method stub
		this.personas.add(p);
	}
	
	
	
	
}
