package org.ministerioEconomia.claseCuatro;

import java.util.Scanner;

public class Consola {

	public static String input(Scanner scanner, String mensaje) {
		System.out.println(mensaje);
		String valor = scanner.nextLine();
		while (valor.isBlank()) {
			System.out.println(mensaje);
			valor = scanner.nextLine();
		} /*while*/
		
		return valor;
		
	}/*void input*/
	
	public static int inputInt(Scanner scanner, String mensaje) {
 	   Integer entrada = null; 
 	   while (entrada==null) {
 		   try {
 			  entrada = Integer.parseInt(Consola.input(scanner, mensaje));
 			  //Opciones
 	    	  //int edad = Integer.valueOf(Consola.input(lector, "Ingrese su edad:"));
 	    	  //int edad = Integer.decode(Consola.input(lector, "Ingrese su edad:"));

 		   } catch (Exception err) {
 			  entrada = null;
 		   }
 	   }
 	   return entrada;
	}
	
}/*class consola*/
