package org.ministerioEconomia.claseCuatro;

import java.text.*;
import java.util.*;

public class Program {

	public static void main(String[] args) {
		System.out.println("Bienvenidos a la Clase Cuatro.");
		System.out.println("Hoy es viernes!!!.");
		System.out.println("------------------------------");

		try (Scanner lector = new Scanner(System.in)) {

			// -------------------------------------------------------------------------------------------------------------------------------
//         // Ejemplo 1
//         // Declaracion de vectores
//         int[] numeros = { 10,20,30,40,50 };
//       
//         System.out.println("Primer Elemento : " + numeros[0]);
//         System.out.println("Cantidad de elementos: " + numeros.length);
//         System.out.println("Utlimo elemento :" + numeros[numeros.length - 1]);
//         System.out.println(MessageFormat.format("[ {0}, {1}, {2}, {3}, {4} ]",  numeros[0], numeros[1], numeros[2], numeros[3], numeros[4]));
			// -------------------------------------------------------------------------------------------------------------------------------

			// -------------------------------------------------------------------------------------------------------------------------------
//    	   // Ejemplo 2
//    	   // Listas
//    	   List<String> sinInstanciar = null;  
//    	   System.out.println("Las variables sin instanciar tienen :" + sinInstanciar);  //Las variale objeto que no instancio tienen null
//    	   
//    	   List<String> nombres = new ArrayList<>();
//    	   System.out.println("Ingrese un nombre, vacio para terminar");
//    	   String nombre = "";
//    	   do {
//    		   nombre = lector.nextLine();
//    		   if (!nombre.isBlank()) {
//    			   nombres.add(nombre);
//    			   System.out.println("Ingrese otro nombre");
//    		   }  		   
//    	   }while (!nombre.isBlank());
//    	   
//    	   //Mostrando con un for convencinal
//    	   System.out.println("Muestro la lista con un for convencional");
//    	   for (int i=0; i<nombres.size(); i++) {
//    		   System.out.println(nombres.get(i));
//    	   }
//    	   
//    	   System.out.println("------------------------------------");
//    	   
//    	   System.out.println("Muestro la lista con un for each");
//    	   for (String elemento : nombres) {
//    		   System.out.println(elemento);
//    	   }
//    	   
//    	   System.out.println("------------------------------------");
//    	   System.out.println("Muestro la lista con una expesion lambda");
//    	   nombres.forEach(elemento -> System.out.println(elemento) );
//    	   
//    	   System.out.println("------------------------------------");
//    	   System.out.println("Muestro la lista con una expesion lambda (version 2)");
//    	   nombres.forEach( System.out::println );
//
//    	   
			// -------------------------------------------------------------------------------------------------------------------------------

//    	   //Ejemplo 3
//    	   //List<int> numeros = new ArrayList<>();  No se puede
//    	   List<Integer> numeros = new ArrayList<>();  
//    	   Integer n = 2;  //boxing y unboxig : pasa se untipo de dato primitivo a un tipo de dato objeto

			// -------------------------------------------------------------------------------------------------------------------------------

			// Ejemplo 4 :
//    	   String nombre = Consola.input(lector, "Ingrese su nombre:");
//    	   String apellido = Consola.input(lector, "Ingrese su apellido:");
//    	   int edad = Consola.inputInt(lector, "Ingrese su edad:");
//    	   System.out.println(MessageFormat.format("{0},  {1} ({2})", apellido.toUpperCase(), nombre, edad));

			// -------------------------------------------------------------------------------------------------------------------------------

			// Ejemplo 5 : Casi objetos (le falta algo)
			// Vamos a mudar esas variables al heap
//			Persona persona = new Persona();
//			persona.nombre = Consola.input(lector, "Ingrese su nombre:");
//			persona.apellido = Consola.input(lector, "Ingrese su apellido:");
//			persona.edad = Consola.inputInt(lector, "Ingrese su edad:");
//			System.out.println(MessageFormat.format("{0},  {1} ({2})", persona.apellido.toUpperCase(), persona.nombre,
//					persona.edad));
			
			//Ejemplo 6 : Mini Ejercicio
			//Quiero cargar una lista de 3 personas y luego mostrarlaa por pantalla
			List<Persona> personas = new ArrayList<>();
			for (int i=1; i<=3; i++) {
				System.out.println("Ingrese la persona "+ i);
				Persona persona = new Persona();
				persona.nombre = Consola.input(lector, "Ingrese su nombre:");
				persona.apellido = Consola.input(lector, "Ingrese su apellido:");
				persona.edad = Consola.inputInt(lector, "Ingrese su edad:");
				personas.add(persona);
			}
			personas.forEach(p -> { 
			   System.out.println("Nombre %s , Apellido %s, Edad: %d".formatted(p.nombre, p.apellido, p.edad));
			});
			


		} /* try scanner */
	} /* void main */
} /* class Program */
