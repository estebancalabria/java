package org.ministerioTrabajo.claseunospring.services;

import java.util.List;

import org.ministerioTrabajo.claseunospring.dto.PersonaDto;

public interface PersonaService {
	List<PersonaDto> findAll();
	PersonaDto findById(int id);
	void deleteById(int id);
	void agregar(PersonaDto personaDto);
}
