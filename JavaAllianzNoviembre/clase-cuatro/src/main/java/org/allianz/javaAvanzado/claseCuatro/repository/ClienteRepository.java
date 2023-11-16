package org.allianz.javaAvanzado.claseCuatro.repository;

import java.util.List;

import org.allianz.javaAvanzado.claseCuatro.models.*;

public interface ClienteRepository {
	Cliente findById(int id);
	List<Cliente> findAll();
	void insert(Cliente nuevo);
	void update(Cliente existente);
	void delete(int id);
}
	
