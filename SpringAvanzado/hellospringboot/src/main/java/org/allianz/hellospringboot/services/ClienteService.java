package org.allianz.hellospringboot.services;

import java.util.*;

import org.allianz.hellospringboot.exceptions.*;
import org.allianz.hellospringboot.models.Cliente;
import org.allianz.hellospringboot.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public List<Cliente> getAll() {
		return this.repository.findAll();
	}
	
	public Cliente getById(int id) {
		Optional<Cliente>  result = this.repository.findById(id);
		
		if (!result.isPresent()) {
			throw new RuntimeException("Cliente no encontrado");
		}
		
		return result.get();
	}

	public void add(Cliente cliente) {
		
		if ((cliente.getNombre()==null) || cliente.getNombre().length()==0) {
			throw new RuntimeException("El nombre del cliente no puede quedar vacio");
		}
		
		this.repository.save(cliente);
	}
	
	public void delete(int id) {
		Optional<Cliente> result = this.repository.findById(id);
		
		if (!result.isPresent()) {
			throw new NotFoundException();
		}
		
		this.repository.delete(result.get());
	}

}
