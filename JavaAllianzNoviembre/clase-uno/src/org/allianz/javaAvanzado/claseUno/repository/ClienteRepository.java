package org.allianz.javaAvanzado.claseUno.repository;

import java.util.List;
import org.allianz.javaAvanzado.claseUno.models.*;

public interface ClienteRepository {
	Cliente findById(int id);
	List<Cliente> findAll();
	void insert(Cliente nuevo);
	void update(Cliente existente);
	void delete(int id);
}
	
