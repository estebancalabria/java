package org.itnow.javaintermedio.proyectofinal.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.itnow.javaintermedio.proyectofinal.models.Ticket;
import org.itnow.javaintermedio.proyectofinal.servicios.TicketService;

//
public class TicketController {

	// Le saco el static a tickets
	/*
	 * private List<Ticket> tickets =new ArrayList<>( Arrays.asList( new
	 * Ticket("Inicidencia 1"), new Ticket("Inicidencia 2"), new
	 * Ticket("Inicidencia 3") ));
	 */
	private TicketService service = new TicketService();

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
		try {
			List<Ticket> tickets = this.service.obtenerTickets();
			sb.append("<h1>Se muestran los tickets</h1> \n");

			if (tickets.size() == 0) {
				sb.append("<h2> No hay tickets</h2> \n");
			} else {
				sb.append("<ul> \n");
				tickets.forEach((t) -> {
					sb.append("   <li>" + t + "</li> \n");
				});
				sb.append("</ul> \n");
			}
		} catch (Exception ex) {
			sb.append("<h1>Error al recuperar los tickets</h1> \n");
			sb.append("<h2> " + ex.getMessage() + " </h2> \n");
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
		// Ticket nuevo = new Ticket(descripcion);
		// this.tickets.add(nuevo);
		try {
			Ticket agregado = this.service.agregarTicket(descripcion);
			return new Vista("<h1> Ticket agregado satisfactoriamente " + agregado.getId() + "</h1> /n");
		} catch (Exception ex) {
			return new Vista("<h1> Error al agregar el ticket : " + ex.getMessage() + "</h1> /n");
		}
	}
}
