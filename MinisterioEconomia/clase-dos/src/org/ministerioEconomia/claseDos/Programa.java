package org.ministerioEconomia.claseDos;

import java.util.*; //Puedo poner el * en vez java.util.Scanner

public class Programa {

	public static void main(String[] args) {
		System.out.println("Bienvenidos a la Clase Dos");

		//
		// Estructuras de control
		//

		try (Scanner lector = new Scanner(System.in)) {

			// Ejemplo 1 : IF

			/*
			 * System.out.println("Ingrese su nombre:");
			 * 
			 * String nombre = lector.nextLine();
			 * 
			 * System.out.println("Ingrese su edad:"); int edad = lector.nextInt();
			 * 
			 * //Estructura de control if if (edad >= 18) { System.out.println("Hola " +
			 * nombre + " eres mayor de edad"); } else { System.out.println("Hola " + nombre
			 * + " eres menor de edad"); }
			 * 
			 * //Alternativa con un operador ternario //System.out.println("Hola " + nombre
			 * + ((edad>18) ? " eres mayor de edad" : " eres menor de edad") );
			 * 
			 * //Version con variables explicativas //boolean esMayorDeEdad = (edad>=18);
			 * //System.out.println("Hola " + nombre + (esMayorDeEdad ?
			 * " eres mayor de edad" : " eres menor de edad") );
			 */

			// Ejemplo 2 : For
			/*
			 * System.out.println("Cuenta hasta 10:"); for (int i=1; i<=10; i++) {
			 * System.out.println(i); }
			 */

			/*
			 * System.out.println("Cuenta regresiva desde 10:"); for (int i=10; i>=0; i--) {
			 * System.out.println(i); }
			 */

			// Calculo si un numero es par o no
			/*
			 * System.out.println("Ingrese un numero"); int numero = lector.nextInt(); if
			 * ((numero % 2) == 0) { System.out.println("El numero es par"); } else {
			 * System.out.println("El numero es impar"); }
			 */

			// Ejercicio : Mostrarme los numeros pares entre 1 y 20 (estructuras de control
			// anidadas)
			System.out.println("Los números pares entre el 1 y el 20 son:");
			for (int i = 1; i <= 20; i++) {
				if (i % 2 == 0) {
					System.out.println(i);
				}
			}

			// Ejemplo 3 : while
		}

	}

}
