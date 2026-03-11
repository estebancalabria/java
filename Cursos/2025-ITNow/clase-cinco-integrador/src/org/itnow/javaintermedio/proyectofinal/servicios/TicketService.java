package org.itnow.javaintermedio.proyectofinal.servicios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.itnow.javaintermedio.proyectofinal.models.Ticket;
import org.itnow.javaintermedio.proyectofinal.persistencia.Repository;
import org.itnow.javaintermedio.proyectofinal.persistencia.TicketDatabaseRepository;

public class TicketService {
	
	/*private List<Ticket> tickets =new ArrayList<>( Arrays.asList(
			new Ticket("Inicidencia 1"),
			new Ticket("Inicidencia 2"),
			new Ticket("Inicidencia 3")
		));*/
	
	
	public List<Ticket> obtenerTickets() throws Exception {
		Repository<Ticket> repo = new TicketDatabaseRepository();
		return repo.getAll();
	}
	
	public Ticket agregarTicket(String descripcion) throws ServiceValidationException, SQLException {
		
		Repository<Ticket> repo = new TicketDatabaseRepository();
		
		if ((descripcion==null) || descripcion.trim().length() ==0) {
			throw new ServiceValidationException("La descripcion del ticket no puede quedar vacia");
		}
		
		Ticket nuevo = new Ticket(descripcion);
		repo.save(nuevo);
		return nuevo;
	}
}
