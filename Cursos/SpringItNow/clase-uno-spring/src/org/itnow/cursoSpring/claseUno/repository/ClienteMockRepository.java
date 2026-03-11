package org.itnow.cursoSpring.claseUno.repository;

import java.util.*;

import org.itnow.cursoSpring.claseUno.models.Cliente;
import org.itnow.cursoSpring.claseUno.utils.Logger;

public class ClienteMockRepository  implements ClienteRepository{

	private List<Cliente> clientes = new ArrayList<Cliente>();
	private Logger logger;
	
	public ClienteMockRepository(Logger logger){
		this.logger = logger;
	}
	
	@Override
	public void insert(Cliente cliente) {
//		System.out.println("(MOCK) Se inserta en memoria");
		this.logger.log("(MOCK) Se inserta en memoria");
		this.clientes.add(cliente);
	}

	@Override
	public List<Cliente> findAll() {
		//System.out.println("(MOCK) Se recipera de memoria");
		this.logger.log("(MOCK) Se recipera de memoria");
		//return new ArrayList<Cliente>();
		return this.clientes;
	}

}
