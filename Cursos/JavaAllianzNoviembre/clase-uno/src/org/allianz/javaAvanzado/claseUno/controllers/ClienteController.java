package org.allianz.javaAvanzado.claseUno.controllers;

import java.util.*;

import org.allianz.javaAvanzado.claseUno.infraestructura.*;
import org.allianz.javaAvanzado.claseUno.models.*;
import org.allianz.javaAvanzado.claseUno.services.*;


public class ClienteController {

	//ClienteRepository repo = new ClienteDatabaseRepository(new ConsoleLog(), "jdbc:sqlite:prod.db");
	private ClienteService service;
	
	public ClienteController(ClienteService service) {
		this.service = service;
	}
	
	//http://localhost/cliente/
	public String get() {
		NewLineStringBuilderDecorator sb = new NewLineStringBuilderDecorator(new StringBuilder());
		
		try {
			List<Cliente> clientes = this.service.obtenerTodos();
			sb.append("<!doctype html>");
			sb.append("<html>");
			sb.append("<head>");
			sb.append("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css\" integrity=\"sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N\" crossorigin=\"anonymous\">");
			sb.append("</head>");
			sb.append("<body>");
			sb.append(" <h1 class='alert alert-info text-center'>Mis Clientes</h1>");
			
			sb.append(" <table class='table' style='width:100%'>");
			sb.append("  <thead>");
			sb.append("    <tr><td>Documento</td><td>Nombre</td><td>Apellido</td></tr>");
			sb.append("  </thead>");
			sb.append("  <tbody>");
			
			clientes.forEach(c -> {
				sb.append(String.format("    <tr><td>%d</td><td>%s</td><td>%s</td></tr>",
						c.getDocumento(), c.getNombre(), c.getApellido()));
			});
			
			sb.append("  </tbody>");
			sb.append(" </table>");
			
			sb.append("</body>");
			sb.append("</html>");
			

		} catch (ServiceException e) {
			sb.append("<h1>error" + e.getMessage() + "</h1>");
		}
		
		return sb.toString();
		
	}
}
