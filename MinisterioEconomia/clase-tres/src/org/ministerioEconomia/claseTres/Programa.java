package org.ministerioEconomia.claseTres;

import java.text.MessageFormat;
import java.util.*;

public class Programa {

	//Constantes para mejorar la legibilidad codigo
	/*public static final int RANGO_MINIMO_EDAD = 0;
	public static final int RANGO_MAXIMO_EDAD = 100;
	public static final int EDAD_MAYOR_DE_EDAD = 18;
	public static final int EDAD_JUBILACION = 70;*/
	
	
	public static void main(String[] args) {
		System.out.println("Bienvendios a la Clase Tres");

		try (Scanner lector = new Scanner(System.in)) {

			// Quiero preguntarle la edad al usuario
			// Ejemplo 1 - El and y or

////------------------------------------------------------------------------------------						
//			//Version 1:
//			System.out.println("Ingrese su edad");
//			int edad = lector.nextInt();
//			//Pecado Capital 3 : Uso excesivo de ifs
//			/*if (Edad.estaDentroDelRango(edad)) {
//				System.out.println("Es una edad valida");
//			} else {
//				System.out.println("Edad fuera de rango");
//			} /* if */
//			
//			//Mejor con un ternario
//			//System.out.println(Edad.estaDentroDelRango(edad) ? "Es una edad valida" : "Edad fuera de rango");
////---------------------------------------------------------------------------------------

////----------------------------------------------------------------------------------------
//			//Version 2: Quiero preguntarle al usuario la edad hasta que me ingrese algo correcto
//			//Estructura de control : While
//			System.out.println("Ingrese su edad");
//			int edad = lector.nextInt(); 
//			while (!Edad.estaDentroDelRango(edad)) {
//				System.out.println("Edad Fuera de Rango, vuelva a ingresar");
//				edad = lector.nextInt();
//			}
////----------------------------------------------------------------------------------------
			
////----------------------------------------------------------------------------------------
//			//Version 3 : Do While
			int edad = 0;
			do {
				//Pecadito : Concatenar mucho String
				//System.out.println("Ingrese su edad entre " + Edad.RANGO_MINIMO + " y " + Edad.RANGO_MAXIMO);
				//System.out.format("Ingrese su edad entre %d y %d :", Edad.RANGO_MINIMO, Edad.RANGO_MAXIMO);
				System.out.print(MessageFormat.format("Ingrese su edad entre {0} y {1} :", Edad.RANGO_MINIMO, Edad.RANGO_MAXIMO));
				edad = lector.nextInt();
			}while(!Edad.estaDentroDelRango(edad));
////----------------------------------------------------------------------------------------
			
			
			//Si la edad es menor a 18 o mayor a 70 la persona no esta en condiciones de ver la pelicula
			//Mostramos una advertencia
			//Esta vez no hace falta un else.
			if ((edad < Edad.MAYORIA_DE_EDAD) || (edad > Edad.JUBILACION)) {
				System.out.println("No esta en condiciones de ver la película");
			} else {
				System.out.println("Bienvenido, la película esta por comenzar...");
			}
			/* if */

		} /* try */

	}/* main */

}/* class Programa */
