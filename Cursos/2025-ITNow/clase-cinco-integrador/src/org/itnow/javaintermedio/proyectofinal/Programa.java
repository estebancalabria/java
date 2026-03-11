package org.itnow.javaintermedio.proyectofinal;

import java.util.Scanner;

import org.itnow.javaintermedio.proyectofinal.controllers.TicketController;

public class Programa {
	
	private static TicketController controller = new TicketController();


	public static void main(String[] args) {
		System.out.println("Curso Java Intermedio");
		System.out.println("Proyecto Final Integrador");
		System.out.println("-------------------------");
		
		Scanner scanner = new Scanner(System.in);

		try {
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
	
	public static void agregarTicket(Scanner scanner) {
		System.out.println(Programa.controller.menuAgregarTicket());
		String descripcion = scanner.next();
	    System.out.println(Programa.controller.agregarTicket(descripcion));
	}
}
