package itnow.javaintermedio.clasetres.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import itnow.javaintermedio.clasetres.models.Ticket;

//
public class TicketController {
	
	//Le saco el static a tickets
	private List<Ticket> tickets =new ArrayList<>( Arrays.asList(
			new Ticket("Inicidencia 1"),
			new Ticket("Inicidencia 2"),
			new Ticket("Inicidencia 3")
		));

	public Vista menuPricipal() {
		String menu = """
				    <h3>Menu Principal</h3>
					<ul>
						<li>1 - Listar Tickets</li>
						<li>2 - Agregar Ticket</li>
						<li>3 - Salir</li>
					</ul>
				""";
		return new Vista(menu);
	}
	
	
	public Vista listaTickets() {
		StringBuilder sb = new StringBuilder();
		sb.append("<h1>Se muestran los tickets</h1> \n");
		
		if (this.tickets.size()==0) {
			sb.append("<h2> No hay tickets</h2> \n");
		} else {
			sb.append("<ul> \n");
			this.tickets.forEach( (t)-> {sb.append("   <li>" + t +"</li> \n"); } );
			sb.append("</ul> \n");
		}

		return new Vista(sb.toString());
	}
	
	public Vista menuAgregarTicket() {
		String menu = """
					<h1>Se agrega un tickets</h1>
					<h2>Ingrese el la descripcion del ticket</h2>
				""";
		return new Vista(menu);
	}
	
	
	public Vista agregarTicket(String descripcion) {
		Ticket nuevo = new Ticket(descripcion);
		this.tickets.add(nuevo);
		return new Vista("<h1> Ticket agregado satisfactoriamente " + nuevo.getId() + "</h1> /n");
	}
}
