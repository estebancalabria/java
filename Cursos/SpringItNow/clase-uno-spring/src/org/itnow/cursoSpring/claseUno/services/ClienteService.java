package org.itnow.cursoSpring.claseUno.services;

import java.util.List;

import org.itnow.cursoSpring.claseUno.models.Cliente;
import org.itnow.cursoSpring.claseUno.repository.*;

public class ClienteService {
	
	//Ejemplo 3 : Sin Inyeccion de Dependencias
	/*private ClienteRepository repository; 
	
	public ClienteService() {
		//Si quiero usar el mock tengo que cambiar esta clase
		//this.repository = new ClienteDatabaseRepository();
		this.repository = new ClienteMockRepository();  //No esta tan bueno
	}*/
	
	//Ejemplo 4 : Con Inyeccion de Dependencia
	//            Inyeccion por constructor
    /*private ClienteRepository repository; 
	
	public ClienteService(ClienteRepository repo) {
		this.repository = repo;
	}*/
	
	//Ejemplo 5 : Inyeccion de dependencias por setter
	/*private ClienteRepository repository;
	
	public void setRepository(ClienteRepository repo) {
		this.repository = repo;
	}*/
	
	//Ejemplo 6 : Inyeccion de deependencia con constructor por Spring
	/*private ClienteRepository repository; 
	
	public ClienteService(ClienteRepository repo) {
		this.repository = repo;
	}*/
	
	//Ejemplo 7 : Agrego el setter para inyeccion de dependencia por setter
	private ClienteRepository repository;
	
	public void setRepository(ClienteRepository repo) {
		this.repository = repo;
	}
	
	public void agregarCliente(Cliente cliente) {
		//Adicionalmente valido la informacion
		this.repository.insert(cliente);
	}
	
	public List<Cliente> recuperarTodos(){
		return this.repository.findAll();
	}

}
