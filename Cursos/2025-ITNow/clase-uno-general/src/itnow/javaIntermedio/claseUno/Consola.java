package itnow.javaIntermedio.claseUno;

import java.util.Scanner;
import java.util.function.Function;

public class Consola {
	private static Scanner scanner = new Scanner(System.in);
	
	public static double readDouble(String mensaje) {
		/*double valor = 0;
		boolean valido = false;
		
		while(!valido) {
			System.out.println(mensaje);
			try {
				valor = Double.parseDouble(scanner.nextLine());
				valido = true;
			} catch (Exception ex) {
				System.out.println("Entrada Invalida. Vuelva a Ingresar");
			}
		}*/
		
		return read(mensaje, Double::parseDouble);
	}
	
	public static int readInteger(String mensaje) {
		/*int valor = 0;
		boolean valido = false;
		
		while(!valido) {
			System.out.println(mensaje);
			try {
				valor = Integer.parseInt(scanner.nextLine());
				valido = true;
			} catch (Exception ex) {
				System.out.println("Entrada Invalida. Vuelva a Ingresar");
			}
		}*/
		
		return read(mensaje, Integer::parseInt);
	}
	
	public static <T> T read(String mensaje, Function<String, T> convertidor) {
		T valor = null;
		boolean valido = false;
		
		while(!valido) {
			System.out.println(mensaje);
			try {
				valor = convertidor.apply(scanner.nextLine());
				valido = true;
			} catch (Exception ex) {
				System.out.println("Entrada Invalida. Vuelva a Ingresar");
			}
		}
		
		return valor;
	}
	
}
