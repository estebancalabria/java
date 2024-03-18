package org.indra.claseSeis.persistence;

import java.util.List;

import org.indra.claseSeis.models.Cliente;

public interface ClienteRepository {
	List<Cliente> findAll() throws Exception;
	Cliente findById(int id) throws Exception;
	void save(Cliente c) throws Exception;
	void update(Cliente c) throws Exception;
	void delete(int id);
}
