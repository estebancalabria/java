package org.ministerioTrabajo.claseunospring.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.ministerioTrabajo.claseunospring.models.Persona;
import org.ministerioTrabajo.claseunospring.services.NoEncontradoException;
import org.springframework.stereotype.Repository;

//Simularia mi acceso a datos
@Repository
public class PersonaRepository implements GenericRepository<Persona, Integer> {

	private List<Persona> personas = new ArrayList<>(Arrays.asList(new Persona(1, "Esteban", 43), new Persona(2, "Diego", 32),
			new Persona(3, "Javier", 36)));
	
	@Override
	public Persona save(Persona entity) {
		// Haria un insert
		if (entity.getId() == 0) {
			entity.setId( this.personas.stream().map( p -> p.getId() ).max(Integer::compare).orElse(0) + 1);
			this.personas.add(entity);
		}
		
		return entity;
	}

	@Override
	public Optional<Persona> findById(Integer id) {
		return this.personas.stream().filter(p -> p.getId() == id).findFirst();
	}

	@Override
	public void deleteById(Integer id) {
		if (!this.personas.removeIf(p -> p.getId() == id)) {
			throw new NoEncontradoException("No se puede eliminar porque no existe persona con id " + id);			
		}
	}

	@Override
	public List<Persona> findAll() {
		return this.personas;
	}

}
