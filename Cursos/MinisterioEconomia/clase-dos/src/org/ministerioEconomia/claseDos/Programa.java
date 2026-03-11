package org.ministerioEconomia.claseDos;

import java.util.*; //Puedo poner el * en vez java.util.Scanner

public class Programa {

	public static void main(String[] args) {
		System.out.println("Bienvenidos a la Clase Dos");

		//
		// Estructuras de control
		//

		try (Scanner lector = new Scanner(System.in)) {

//			// Ejemplo 1 : IF
//			System.out.println("Ingrese su nombre:");
//			String nombre = lector.nextLine();
//
//			System.out.println("Ingrese su edad:");
//			int edad = lector.nextInt();
//
//			// Estructura de control if
//			if (edad >= 18) {
//				System.out.println("Hola " + nombre + " eres mayor de edad");
//			} else {
//				System.out.println("Hola " + nombre + " eres menor de edad");
//			}

//			// Alternativa con un operador ternario 
//			System.out.println("Hola " + nombre + ((edad>18) ? " eres mayor de edad" : " eres menor de edad") );
//			
//			//Version con variables explicativas 
//			boolean esMayorDeEdad = (edad>=18);
//			System.out.println("Hola " + nombre + (esMayorDeEdad ?" eres mayor de edad" : " eres menor de edad") );
			

//			// Ejemplo 2 : For
//			// Ejemplo ascendente
//			System.out.println("Cuenta hasta 10:");
//			for (int i = 1; i <= 10; i++) {
//				System.out.println(i);
//			}

//			//Ejemplo descendente
//			 System.out.println("Cuenta regresiva desde 10:"); 
//			 for (int i = 10; i >= 0; i--) { 
//				 System.out.println(i); 
//			 }

//			// Calculo si un numero es par o no
//			System.out.println("Ingrese un numero");
//			int numero = lector.nextInt();
//			if ((numero % 2) == 0) {
//				System.out.println("El numero es par");
//			} else {
//				System.out.println("El numero es impar");
//			}

//			// Ejercicio : Mostrarme los numeros pares entre 1 y 20 (estructuras de control
//			// anidadas)
//			System.out.println("Los números pares entre el 1 y el 20 son:");
//			for (int i = 1; i <= 20; i++) {
//				if (i % 2 == 0) {
//					System.out.println(i);
//				}
//			}

//			// Ejericio sumatoria
//			// Mostrar la sumatoria de los numeros entre 1 y 100000
//			// 1+2+3+4+5=+....+100000
//			 int total = 0; 
//			 for (int i = 1; i <= 100000; i++) { 
//				 total = total + i; 
//		     }
//			 System.out.println("El sumatorio de los primeros 100.000 números es: " + total);
			 
			
//			//Ejercicio contar la cantidad de numeros impares entre 1 y 500
//			int cantidadNumerosImpares = 0;
//			for (int i=1; i<=500; i++) {
//				boolean esImpar = (i % 2) == 1;  //El numero va a ser impar si el resto de divicir i por 2 es 1
//				if (esImpar) {
//					//cantidadNumerosImpares = cantidadNumerosImpares  + 1;
//					cantidadNumerosImpares++;
//				} /*if*/
//			}/* for */
//			System.out.println("La cantidad de numeros impares es: " + cantidadNumerosImpares);
			
			//Ejercicio mostrar los numeros mutiplos de 5 entre 1 y 100
			//Bien agustin, version sin el if
//			System.out.println("Los numeros multiplos de 5 entre 1 y 100 son");
//			for (int i = 0; i <= 100; i=i+5) { 
//				System.out.println (i);
//			}
			
//			//Version 2: con el IF
//			System.out.println("Los numeros multiplos de 5 entre 1 y 100 son");
//			for (int i = 0; i <= 100; i++) { 
//				boolean esMultiploDe5 = (i%5) == 0;
//				if (esMultiploDe5) {
//					System.out.println (i);
//				}
//			}
			
			//Dado un numero que igrese el usuario, decir si es primo
			System.out.println("Ingrese un numero y se le dira si es primo.");
			int numeroIngresado = lector.nextInt();
			boolean esPrimo = true; 
			for (int i=2; i<numeroIngresado; i++) {
				if ((numeroIngresado % i)==0) {
					esPrimo = false;
				}
			} 
			System.out.println(esPrimo ? "Es Primo" : "No es primo");

//			// Ejercicio promedio
//			// Calcular el promedio de los numeros impares entre 1 y 1000
//			// ! es el operador de negacion.
//			int totalImpares = 0;
//			int contador = 0;
//			double promedio = 0;
//			for (int i = 1; i <= 1000; i++) {
//				boolean esImpar = !((i % 2) == 0);
//				if (esImpar) {
//					totalImpares = totalImpares + i;
//					contador = contador + 1;
//				}
//			}
//			promedio = totalImpares / contador;
//			System.out.println("El promedio de los impares entre 1 y mil es: " + promedio);

			// Ejemplo 3 : while
			
		} /*try (Scanner lector = new Scanner(System.in))*/
	} /* void main */	
} /*class Programa*/
