package org.allianz.javaAvanzado.claseCuatro.repository;

import java.util.*;

import org.allianz.javaAvanzado.claseCuatro.infraestructura.Log;
import org.allianz.javaAvanzado.claseCuatro.models.Cliente;

public class ClienteMemoryRepository implements ClienteRepository {

	private List<Cliente> clientes;
	private Log log;
	
	public ClienteMemoryRepository(Log log) {
		this.log = log;
		this.clientes = new ArrayList<Cliente>();
	}
	
	@Override
	public Cliente findById(int id) {
		log.info("Cliente Repository : Find By Id " + id);
		//Sin Expresion Lambda
		/*Cliente result = null;
		for (Cliente c : this.clientes) {
			if (c.getId() == id) {
				result = c;
			}
		}
		return result;*/
		
		//Con expresion Lambda
		return this.clientes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
		
		//Mas Explicado
		//List<Cliente> clientesFiltrados = this.clientes.stream().filter(c -> c.getId() == id).toList();
		//return clientesFiltrados.stream().findFirst().orElse(null);		
	}

	@Override
	public List<Cliente> findAll() {
		log.info("Cliente Repository : Find All " + this.clientes);
		return this.clientes;
	}

	@Override
	public void insert(Cliente nuevo) {
		log.info("Cliente Repository : Insertando al cliente " + nuevo.toString());
		this.clientes.add(nuevo);
	}

	@Override
	public void update(Cliente existente) {
		log.info("Cliente Repository : Actualizando al cliente " + existente.toString());
		Cliente remplazo = this.findById(existente.getId());
		this.clientes.remove(remplazo);
		this.clientes.add(existente);
	}

	@Override
	public void delete(int id) {
		//Todo: Incorporar manejo de excepciones
		log.info("Cliente Repository : Eliminando al cliente con id" + id);
		Cliente existente = this.findById(id);
		if (existente != null) {
			this.clientes.remove(existente);	
		}
	}

}
