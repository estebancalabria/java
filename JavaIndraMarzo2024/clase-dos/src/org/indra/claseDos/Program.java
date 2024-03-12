package org.indra.claseDos;

import java.util.*;

import org.indra.gaming.JuegoAdivinanzas;
import org.indra.io.*;

public class Program {

	public static void main(String[] args) {
		// Ejemplo 1 :
		// Scanner scanner = new Scanner(System.in);

		// Quiero que si no ingreso nada, me vuelva a preguntar el nombre
		// Quiero hcerlo en un ciclo hasta que la entrada no sea vacia
		// Quiero que avise si ha ingresado algo vacio

		// version 1 - con do...while
		/*
		 * System.out.println("Ingrese su nombre:"); String nombre = ""; do { nombre =
		 * scanner.nextLine(); } while (nombre.trim().equals(""));
		 */

		// version 2 - con while (con lectura adelantada)
		/*
		 * System.out.println("Ingrese su nombre:"); String nombre = scanner.nextLine();
		 * while (nombre.trim().length()==0) {
		 * System.out.println("Entrada vacia. Vuelva a ingresar"); nombre =
		 * scanner.nextLine(); }
		 */

		// Version 3. Usando una clase
		/*
		 * System.out.println("Ingrese su nombre:"); String nombre =
		 * Console.readNonEmptyLine(); System.out.println("Ingrese su apellido:");
		 * String apellido = Console.readNonEmptyLine();
		 * System.out.format("Bienvenido %s %s", nombre, apellido);
		 */

		// Version 4. Usando el metodo prompt
		/*
		 * String nombre = Console.prompt("Ingrese su nombre:"); String apellido =
		 * Console.prompt("Ingrese su apellido:"); String entrada =
		 * Console.prompt("Ingrese su edad");
		 * 
		 * int edad = 0; try { edad = Integer.parseInt(entrada); }catch (Exception ex){
		 * System.out.println("Ha fallado la conversion a int"); }
		 * 
		 * //Text Block. En java para que String ocupe varias lineas """P
		 * System.out.format(""" Bienvenido %s %s. Tienes %d años """, nombre, apellido,
		 * edad);
		 */

		// Version 5
		/*
		 * String nombre = Console.prompt("Ingrese su nombre:"); String apellido =
		 * Console.prompt("Ingrese su apellido:"); int edad =
		 * Console.promptInt("Ingrese su edad");
		 * 
		 * //Text Block. En java para que String ocupe varias lineas """P
		 * System.out.format(""" Bienvenido %s %s. Tienes %d años """, nombre, apellido,
		 * edad);
		 */

		// Ejercicio 2 - Juego
		// La computadora determina un numero al azar entre 0 y 100 y el usuario tiene 5
		// opciones para adivinar
		// Se le pregunta al usuario cual cree que es el numero secreto
		// Si adivina gana el juego
		// Si no adivina se le informa si el numero secreto es mayor o menor al numero
		// ingresado
		// Tiene 5 oportunindades o pierde el juego
		
		//Vesion Jorge G.
		/*Random generadorNumeros = new Random();
		int contador = 5;
		int valor = generadorNumeros.nextInt(0, 100);
		boolean adivinado = false;
		int intentar;
		do {
			intentar = Console.promptInt("Intenta adiviniar el numero: ");
			if (intentar == valor) {
				adivinado = true;
			} else if (intentar > valor) {
				System.out.println("El numero es menor al sugerido.");
			} else if (intentar < valor) {
				System.out.println("El numero es mayor al sugerido.");
			}
			--contador;
		} while ((adivinado == false) && (contador > 0));
		
		if (adivinado) {
			System.out.println("Numero acertado.");
		} else {
			System.out.println("Numero no acertado. El numero era " + valor);
		}*/
		
		//Version Alvaro O.
		/*Random generadorNumerosAzar = new Random();
        int numAzar = generadorNumerosAzar.nextInt(0, 100);
        
        int oportunidades = 5;
        
        int entrada = Console.promptInt("Introduce un numero: ");
        
        while (oportunidades != 0){
            if (entrada == numAzar) {
                System.out.println("Felicidades has ganado!!!");
            } else if (entrada < numAzar){
                oportunidades -= 1;
                System.out.println("El numero secreto es mayor.");
                entrada = Console.promptInt("Introduce otro numero: ");
            } else if (entrada > numAzar) {
                oportunidades -= 1;
                System.out.println("El numero secreto es menor.");
                entrada = Console.promptInt("Introduce otro numero: ");
            }
        } */
		
		//Version Separando en una clase aparte
		JuegoAdivinanzas jugar = new JuegoAdivinanzas();
        jugar.jugar();

		// scanner.close();
	}

}
