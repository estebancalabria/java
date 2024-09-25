package org.ministerioTrabajo.claseunospring.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.ministerioTrabajo.claseunospring.dto.PersonaDto;
import org.ministerioTrabajo.claseunospring.models.Persona;
import org.ministerioTrabajo.claseunospring.repositories.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {
	//private List<Persona> personas = new ArrayList<>(Arrays.asList(new Persona(1, "Esteban", 43), new Persona(2, "Diego", 32),
	//		new Persona(3, "Javier", 36)));
	
	@Autowired
	public GenericRepository<Persona, Integer> repo;
	
	private PersonaDto mapModelToDto(Persona persona) {
		PersonaDto result = new PersonaDto();
		result.setId(persona.getId());
		result.setNombre(persona.getNombre());
		result.setEdad(persona.getEdad());
		return result;
	}
	
	private Persona mapDtoToModel(PersonaDto dto) {
		Persona result = new Persona();
		result.setId(dto.getId());
		result.setNombre(dto.getNombre());
		result.setEdad(dto.getEdad());
		return result;
	}
	
	//public List<Persona> findAll(){
	public List<PersonaDto> findAll(){
		//return this.personas;
		//return this.personas.stream().map(this::mapModelToDto).toList();
		return repo.findAll().stream().map(this::mapModelToDto).toList();
	}
	
	//public Persona findById(int id) {
	public PersonaDto findById(int id) {
		//Optional<Persona> buscado = this.personas.stream().filter(p -> p.getId() == id).findFirst();
		Optional<Persona> buscado = this.repo.findById(id);
		
		if (buscado.isEmpty()) {
			//throw new Error("No Encontrado");
			throw new NoEncontradoException("No existe persona con id " + id);
			//devolvemos un 404
			//return ResponseEntity.notFound().build();
			//return new ResponseEntity<>("No se encuentra la persona con ese id", HttpStatus.NOT_FOUND);
		}
		
		return mapModelToDto(buscado.get());
	}

	public void deleteById(int id) {
		/*if (!this.personas.removeIf(p -> p.getId() == id)) {
			throw new NoEncontradoException("No se puede eliminar porque no existe persona con id " + id);			
		}*/
		this.repo.deleteById(id);
	}
	
	//public void agregar(Persona persona) {
	public void agregar(PersonaDto personaDto) {
		
		Persona persona = this.mapDtoToModel(personaDto);
		
		if ( (persona.getNombre() == null) ||  persona.getNombre().isBlank() ) {
			throw new ValidationException("El nombre de la persona no puede quedar vacio");
		}
		
		//Valido que la edad de la persona sea mayor a 0
		if(persona.getEdad() <= 0) {
			throw new ValidationException("Edad de la persona incorrecta");
		}		
		
		//if( this.personas.stream().filter(p -> p.getNombre().equals(persona.getNombre()) ).findFirst().isPresent() )
		//if (repo.findById(persona.getId()).isEmpty())
		if (this.repo.findAll().stream().filter(p -> p.getNombre().equals(persona.getNombre()) ).findFirst().isPresent() )
			throw new ElementoDuplicadoException("No se puede añadir una persona con el mismo nombre que otra existente");
		
		//persona.setId( this.personas.stream().map( p -> p.getId() ).max(Integer::compare).orElse(0) + 1);
		
		//this.personas.add(persona);
		this.repo.save(persona);	
	}
	
}
