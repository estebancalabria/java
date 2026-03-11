import java.util.Scanner;

public class Programa {

	public static void main(String[] args) {
		// Mi primer Hola Mundo
		// System.out.println("Hola Mundo");

		// Convenciones de codigo (camelCase para variables y metodos)
		// System.out.imprimirLinea();
		// System.out.Println("Hola Mundo");

		// Declaracion de variables
		// int numero = 5;
		// int otroNumero = 10;
		// int resultado = numero + otroNumero;
		// System.out.print(resultado);
		// System.out.println(numero + otroNumero);

		// Trabajando con tipos de datos
		// String nombre = "Esteban";
		// String edad = 5; //Type mismatch: cannot convert from int to String
		// int edad = 42;
		// edad = edad + "5"; //Type mismatch: cannot convert from String to int
		// System.out.println("Mi nombre es " + nombre + " y mi edad es " + edad); //
		// Conversion implicita

		// Metodos de variable
		// String nombre = "Esteban";
		// System.out.println(nombre.toUpperCase()); //Siempre que invoco un metodo va
		// () al final
		// System.out.println(nombre.toLowerCase());
		// System.out.println(nombre.substring(0,3));

		// variable.metodo <<< metodo de variable (objeto)
		// Clase.metodo <<< TipoDeDato.metodo <<< metodo de clase (estatico)

		// Conversiones Explicita
		// String numeroRaro = "55";
		//// String numeroRaro = "55a"; //java.lang.NumberFormatException: For input
		// string:
		// int numeroDeBien = 55;
		// int suma = numeroDeBien + Integer.parseInt(numeroRaro);
		// System.out.println(suma);

		/*
		 * int edad = 18; // if (edad >= 18) { //
		 * System.out.println("Usted es mayor de edad"); // }
		 * 
		 * if (edad >= 18) { // (edad>=18) va entre () y es una expresion booleana
		 * System.out.println("Usted es mayor de edad"); } else {
		 * System.out.println("Usted es menor de edad"); }
		 */

		// Bienvenidos a la clase Math
		// Y algo de casteos
		// int numero1 = Math.random(); //Type mismatch: cannot convert from double to
		// int
		// int numero1 = (int)(Math.random() * 100); //(Casteo, descarto la parte
		// decimal y me quedo con la parte entera
		// int numero2 = (int)(Math.random() * 100);
		// if (numero1 > numero2 ) {
		// System.out.println("El numero mayor es " + numero1);
		// }else {
		// System.out.println("El numero mayor es " + numero2);
		// }
		// System.out.println("El numero mayor entre " + numero1 + " y " + numero2 + "
		// es " + Math.max(numero1, numero2));

		// for
		/*
		 * for (int i=1; i<=10; i++) { System.out.println(i); }
		 */

		// while
		/*
		 * int i = 1; while (i<=10) { System.out.println(i); i++; }
		 */

		// Do while que se mee ejecuta al menos una vez
		/*
		 * int i= 1; do { System.out.println(i); i++; }while(i<=10);
		 */

		// vectores, *arreglos*, arrays, tablas, matrices, pseudolistas
		/*
		 * int[] numeros = {34,65,76};
		 * 
		 * //System.out.println(numeros[0]); //La primer posicion (OJO que cambia con
		 * cobol) //System.out.println(numeros[1]); //System.out.println(numeros[
		 * numeros.length - 1 ]); //La ultima posicion
		 * //System.out.println("El arreglo tiene " + numeros.length + " elementos");
		 * //System.out.println(numeros[3]); //java.lang.ArrayIndexOutOfBoundsException:
		 * Index 3 out of bounds for length 3
		 * 
		 * /*for (int i=0; i<numeros.length; i++) { System.out.println(numeros[i]); }
		 */

		// El foreach!
		/*
		 * for (int n : numeros) { System.out.println(n); }
		 */

		///
		// Mas Arreglos...arreglo sin inicializar
		//
		// int[] numeros = new int[1000];

		// Veo lo inicializa en 0
		/*
		 * for (int numero: numeros) { System.out.println(numero); }
		 */

		// for (int i=0; i<=numeros.length-1; i++) {
		/*
		 * for (int i = 0; i < numeros.length; i++) { numeros[i] = (int) ((Math.random()
		 * * 5) + 1); // de 1 a 5 }
		 * 
		 * int cantidad1 = 0; int cantidad2 = 0; int cantidad3 = 0; int cantidad4 = 0;
		 * int cantidad5 = 0;
		 * 
		 * for (int n : numeros) {
		 * 
		 * //El evaluate de cobol en java es... switch (n) { case 1: cantidad1++; break;
		 * case 2: cantidad2++; break; case 3: cantidad3++; break; case 4: cantidad4++;
		 * break; default: cantidad5++; break; }
		 * 
		 * //if (n=1) { //.. //}else if (n=2) { //.. //} //}else if (n=3) { //} }
		 * 
		 * System.out.println("Hay 1: " + cantidad1); System.out.println("Hay 2: " +
		 * cantidad2); System.out.println("Hay 3: " + cantidad3);
		 * System.out.println("Hay 4: " + cantidad4); System.out.println("Hay 5: " +
		 * cantidad5);
		 */

		// Leer datos de la consola
		// String cadena = new String("Hola"); Esto tambien es valido
		/*
		 * Scanner scanner = new Scanner(System.in);
		 * System.out.println("Ingrese su nombre"); String nombre = scanner.nextLine();
		 * System.out.println("Hola " + nombre); scanner.close();
		 */

		// Leer datos de la consola 2
		/*
		 * Scanner scanner = new Scanner(System.in);
		 * System.out.println("Ingrese su nombre"); String nombre = scanner.nextLine();
		 * System.out.println("Ingrese su edad"); try { int edad = scanner.nextInt();
		 * System.out.println("Hola " + nombre + " de " + edad + " años");
		 * }catch(Exception ex) { System.out.println("Hola " + nombre);
		 * System.out.println("No pude entender tu edad");
		 * //System.out.println(ex.getMessage()); //ex.printStackTrace(); }
		 * scanner.close(); System.out.println("Fin del Programa");
		 */

		// Vamos a programar un juego
		// La computadora determina un numero al azar entre 1 y 100
		// El usuario tiene 5 oportunidades para adivinarlo y elije un numero
		// La computadora informa si el numero del usuario es mayor o menor al numero
		// secreo
		// Si adivina gana la partida
		// if (() && ())
		// != es diferente

		int numeroAlAzar = (int) ((Math.random() * 100) + 1); // de 1 a 100
		int oportunidadActual = 0;
		int numeroAdivinado = 0;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Vamos a programar un juego");
		System.out.println("Instrucciones");
		System.out.println("La computadora determina un numero al azar entre 1 y 100");
		System.out.println("Tienes 5 oportunidades para adivinarlo");
		System.out.println("Buena suerte!");

		do {
			oportunidadActual++;
			System.out.println("Ingrese un numero. Oportunindad " + oportunidadActual);
			numeroAdivinado = scanner.nextInt();
			
			if (numeroAlAzar>numeroAdivinado) {
				System.out.println("El numero secreto es mayor");
			} else if (numeroAlAzar<numeroAdivinado) {
				System.out.println("El numero secreto es menor");
			}
			
		} while ((oportunidadActual < 5) && (numeroAdivinado != numeroAlAzar));
		
		// (expresion_booleana) ? devuelve_si_true : devuelve_si_false
		System.out.println( (numeroAdivinado == numeroAlAzar) ? "Felicidades. Has Ganado" : "Perdiste. Segui Participando");

		scanner.close();
	}

}
