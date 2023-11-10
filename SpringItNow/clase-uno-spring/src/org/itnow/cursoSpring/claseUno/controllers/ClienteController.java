package org.itnow.cursoSpring.claseUno.controllers;

import org.itnow.cursoSpring.claseUno.services.*;

//Esta persona se encarga de tomar las peticiones http 
//y armar las vistas
public class ClienteController {
	
	private ClienteService service;
	
	public void setService(ClienteService service) {
		this.service = service;
	}
	
	
	//@PeticionGet("/clientes")
	public String index(){
		StringBuilder sb = new StringBuilder();
		sb.append("<html> \n");
		sb.append("<body> \n");
		sb.append("  <h1>Mi pagina principal de clientes</h1> \n");
		sb.append("  <ul> \n");
		
		this.service.recuperarTodos().forEach( c -> {
			sb.append(String.format("    <li>%s</li> \n", c.getNombre()));
		});
		
		sb.append("  </ul> \n");
		sb.append("</body> \n");
		sb.append("</html>");	
		return sb.toString();
	}
}
