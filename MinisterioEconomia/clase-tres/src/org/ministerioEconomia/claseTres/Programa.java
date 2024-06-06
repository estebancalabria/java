package org.ministerioEconomia.claseTres;

import java.util.*;

public class Programa {

	//Constantes para mejorar la legibilidad codigo
	public static final int RANGO_MINIMO_EDAD = 0;
	public static final int RANGO_MAXIMO_EDAD = 100;
	public static final int EDAD_MAYOR_DE_EDAD = 18;
	public static final int EDAD_JUBILACION = 70;
	
	
	public static void main(String[] args) {
		System.out.println("Bienvendios a la Clase Tres");

		try (Scanner lector = new Scanner(System.in)) {

			// Quiero preguntarle la edad al usuario
			// Ejemplo 1 - El and y or
			System.out.println("Ingrese su edad");
			int edad = lector.nextInt();

			if ((edad > RANGO_MINIMO_EDAD) && (edad < RANGO_MAXIMO_EDAD)) {
				System.out.println("Es una edad valida");
			} else {
				System.out.println("Edad fuera de rango");
			} /* if */
			
			//Si la edad es menor a 18 o mayor a 70 la persona no esta en condiciones de ver la pelicula
			//Mostramos una advertencia
			//Esta vez no hace falta un else.
			if ((edad < EDAD_MAYOR_DE_EDAD) || (edad > EDAD_JUBILACION)) {
				System.out.println("No esta en condiciones de ver la película");
			} /* if */

		} /* try */

	}/* main */

}/* class Programa */
