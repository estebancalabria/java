package org.allianz.clasecincospringboot.repository;

import java.util.List;

import org.allianz.clasecincospringboot.models.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository {
	Cliente findById(int id);
	List<Cliente> findAll();
	void insert(Cliente nuevo);
	void update(Cliente existente);
	void delete(int id);
}
	
