package org.indra.io;

import java.util.*;

public class Console {

	private static Scanner scanner = new Scanner(System.in);

	public static String readNonEmptyLine() {

		String entrada = scanner.nextLine();
		while (entrada.trim().length() == 0) {
			System.out.println("Entrada vacia. Vuelva a ingresar");
			entrada = scanner.nextLine();
		}
		return entrada;
	}

	public static String prompt(String mensaje) {
		System.out.println(mensaje);
		return readNonEmptyLine();
	}

	public static int readInt() {
		boolean reintentar = true;
		int entero = 0;
		
		do {
			try {
				// String entrada = Console.prompt("Ingrese su edad: ");
				String entrada = Console.readNonEmptyLine();
				entero = Integer.parseInt(entrada);
				reintentar = false;
			} catch (Exception ex) {
				System.out.println("Ha fallado la conversion a int.");
				reintentar = true;
			}
		} while (reintentar);
		
		return entero;
	}

	public static int promptInt(String mensaje) {
        System.out.println(mensaje);
        return readInt();
	}

}
