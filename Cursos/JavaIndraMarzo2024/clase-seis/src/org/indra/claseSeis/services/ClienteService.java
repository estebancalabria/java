package org.indra.claseSeis.services;

import java.io.IOException;
import java.time.LocalDate;

import org.indra.claseSeis.models.Cliente;
import org.indra.claseSeis.persistence.*;

//O tiene la logica de negocios. validaciones, etc
//O hace que el modelo la valide
public class ClienteService {

	//dependencia entre el servicio y el repository
	//Esta dependenci asi es fija
	//private ClienteFileRepository clienteRepository = new ClienteFileRepository();
	
	//Refactorizando
	//Inyeccion de dependencia por contructor
	ClienteRepository respository;
	
	public ClienteService(ClienteRepository repository) {
		this.respository = repository;
	}
	
	public void registrarCliente(Cliente c) throws Exception {
		
		//Reglas de negocio
		//Validaciones 
		if (c.getFechaNacimiento().isAfter(LocalDate.now())) {
			throw new Exception("Mal la fecha....");
		}
		
		if (c.getNombre() == null) {
			throw new Exception("Nombre mal");
		}
		//Validaciones...
		
		
		respository.save(c);
	}
	
    //public void actualizarCliente
	//public void darDeBajaCliente

	
	public Cliente recuperarCliente(int c) throws Exception {
        return respository.findById(c);
	}
}
