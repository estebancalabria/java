package org.indra.claseSeis.persistence;

import java.util.*;

import org.indra.claseSeis.models.Cliente;

public class ClienteMockRepository implements ClienteRepository {

	private List<Cliente> clientes = new ArrayList<>();
	
	@Override
	public List<Cliente> findAll() throws Exception {
		// TODO Auto-generated method stub
		return this.clientes;
	}

	@Override
	public Cliente findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return this.clientes.stream().filter(c -> c.getId()== id).findFirst().orElse(null);
	}

	@Override
	public void save(Cliente c) throws Exception {
		// TODO Auto-generated method stub
		
		c.setId(this.clientes.size()+1);
		this.clientes.add(c);
	}

	@Override
	public void update(Cliente c) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

}
