package itnow.javaintermedio.clasetres;

//import java.util.*;  // Asi puedo importar todas las clases de la libreria java.util
//Es mas profesional
import java.util.List;
import java.util.Scanner;

import itnow.javaintermedio.clasetres.controllers.TicketController;
import itnow.javaintermedio.clasetres.models.Ticket;

import java.util.ArrayList;
import java.util.Arrays;

public class Programa {
	
	//REFATORIZACION: Reemplazo la lista de tickets por mi controlador
	//Incializo la lista global al momento de declararla
	/*private static List<Ticket> tickets =new ArrayList<>( Arrays.asList(
		new Ticket("Inicidencia 1"),
		new Ticket("Inicidencia 2"),
		new Ticket("Inicidencia 3")
	));*/
	private static TicketController controller = new TicketController();

	public static void main(String[] args) {
		System.out.println("Bienvenidos a la Clase Tres del curso Java Intermedio de ITNow");
		System.out.println("Esta aplicacion es un ejemplo de aplicacion en capas");
		System.out.println("Esta app es un ejemplo de aplicacion consola para cargar tickets");
		System.out.println("----------------------------------------------------------------");

		Scanner scanner = new Scanner(System.in);

		try {
			// Crear un listado de 3 tickets de ejemplo y mostrarlos
			/*List<Ticket> tickets = new ArrayList<>();
			tickets.add(new Ticket("Inicidencia 1"));
			tickets.add(new Ticket("Inicidencia 2"));
1
			tickets.add(new Ticket("Inicidencia 3"));

			System.out.println("Los tickets que tengo en mi aplicacion son:");
			tickets.forEach(System.out::println); // Estoy llamando al toString() de los objetos*/
			int opcion = 0;
			do {
				System.out.println(Programa.controller.menuPricipal());
				opcion = scanner.nextInt();
				switch (opcion) {
				case 1: 
					System.out.println(Programa.controller.listaTickets());
					break;
				case 2:
					Programa.agregarTicket(scanner);
					break;
				}

			} while (opcion !=3);
			
		} finally {
			scanner.close();
		}

	}
	
	//REFACTORING : Ahora va al controller
	/*public static void mostrarMenu() {
		System.out.println("----Menu Principal----");
		System.out.println("1. Listar Tickets ");
		System.out.println("2. Agregar Ticket ");
		System.out.println("3. Salir ");
	}*/
	
	public static void agregarTicket(Scanner scanner) {
		System.out.println(Programa.controller.menuAgregarTicket());
		String descripcion = scanner.next();
	    System.out.println(Programa.controller.agregarTicket(descripcion));
		
	    //REFACTORING : PARTE DE LA LOGICA AL CONTROLADOR
	    /*/*Ticket nuevo = new Ticket(descripcion);
		Programa.tickets.add(nuevo);
		System.out.println("Ticket agregado satisfactoriamente " + nuevo.getId());
		System.out.println("");*/
	}
	
	// Refactoring : PASA AL CONTROLLER
	/*public static void listarTickets() {
		System.out.println("");
		System.out.println("Se muestran los tickets");
		Programa.tickets.forEach(System.out::println); // Estoy llamando al toString() de los objetos*/
		/*System.out.println("");
	}*/
	
}
