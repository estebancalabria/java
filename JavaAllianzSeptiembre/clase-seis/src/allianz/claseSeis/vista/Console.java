package allianz.claseSeis.vista;

import java.util.Scanner;

public class Console {

	// Esta es ahora una variable compartida para todos los metodos de la clase
	// console
	private static Scanner scanner = new Scanner(System.in);

	public static String DEFAULT_TYPE_INT_ERROR_MESSAGE = "Debe Ingresar un Numero";
	public static String DEFAULT_OUT_OF_RANGE_INT_ERROR_MESSAGE = "El valor debe estar entre %d y %d";

	// Si no quiero que se pueda cambiar desde afuera
	// public static final String DEFAULT_TYPE_ERROR_MESSAGE = "Debe Ingresar un
	// Numero";

	public static void writeLine(String message) {
		System.out.println(message);
	}

	public static String prompt(String message) {
		// Scanner scanner = new Scanner(System.in);
		/*
		 * try { System.out.println(message); return scanner.nextLine(); }finally {
		 * //scanner.close(); }
		 */

		System.out.println(message);
		return scanner.nextLine();
	}

	public static int promptInt(String message, String errorMessage, int minValue, int maxValue) {
		
		if (minValue>maxValue) {
			throw new Error("promptInt : El valor minimo no puede ser mayor al valor maximo. Por favor corrija el error");

		}		
		
		System.out.println(message);
		boolean entradaExitosa = false;
		int result = 0;
		while (!entradaExitosa) {
			try {
				result = scanner.nextInt();
				entradaExitosa = ((result >= minValue) && (result <= maxValue));
				if (!entradaExitosa) {
					writeLine(String.format(DEFAULT_OUT_OF_RANGE_INT_ERROR_MESSAGE, minValue, maxValue));
				}
			} catch (Exception ex) {
				// writeLine("Se esperaba un numero. Vuelva a Intentarlo");
				writeLine(errorMessage);
				scanner.next();
			}
		}
		return result;
	}

	public static int promptInt(String message, String errorMessage) {
		// Scanner scanner = new Scanner(System.in);
		/*
		 * try { System.out.println(message); return scanner.nextInt(); }finally {
		 * //scanner.close(); }
		 */
		
		/*System.out.println(message);
		boolean entradaExitosa = false;
		int result = 0;
		while (!entradaExitosa) {
			try {
				result = scanner.nextInt();
				entradaExitosa = true;
			} catch (Exception ex) {
				// writeLine("Se esperaba un numero. Vuelva a Intentarlo");
				writeLine(errorMessage);
				scanner.next();
			}
		}
		return result;*/
		return promptInt(message, errorMessage, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	// Agrego una sobrecarga (overloading) del promptInt
	public static int promptInt(String message) {
		return promptInt(message, DEFAULT_TYPE_INT_ERROR_MESSAGE);
	}
}
