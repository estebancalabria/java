package itnow.javaIntermedio.clasesUno;

import java.util.Scanner;

public class Consola {
	private static Scanner scanner = new Scanner(System.in);
	
	public static double readDouble(String mensaje) {
		double valor = 0;
		boolean valido = false;
		
		while(!valido) {
			System.out.println(mensaje);
			try {
				valor = Double.parseDouble(scanner.nextLine());
				valido = true;
			} catch (Exception ex) {
				System.out.println("Entrada Invalida. Vuelva a Ingresar");
			}
		}
		
		return valor;
	}
	
}
