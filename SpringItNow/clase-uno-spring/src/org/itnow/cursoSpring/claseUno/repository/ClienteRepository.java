package org.itnow.cursoSpring.claseUno.repository;

import java.util.List;

import org.itnow.cursoSpring.claseUno.models.Cliente;

public interface ClienteRepository {
	
	void insert(Cliente cliente);
	List<Cliente> findAll();
}
