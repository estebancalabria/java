package org.indra.claseUno;

public class Program {

	public static void main(String[] args) {
		// Ejemplo 1 - Hola Mundo
		// System.out.println("Hola Mundo");

		// Ejemplo 2 - Tipos de datos Primitivos (int)
		/*
		 * int numeroUno = 2; int numeroDos = 4; int resultado = numeroUno + numeroDos;
		 * System.out.println("La suma es " + resultado); //Conversion Implicita
		 */

		// Ejemplo 3 - Otros tipos de datos primitivo
		/*
		 * double numeroUno = 2; double numeroDos = 3.0; //int res = numeroUno +
		 * numeroDos; //No funciona, chekeo de tipos int res = ((int)numeroUno) +
		 * ((int)numeroDos); //Conversion Explicita o casteo System.out.println(res);
		 */

		// Ejemplo 3 - Tipos String (tipos de datos objeto, hacemos new)
		// String nombre = "Esteban";
		// String otroNombre = new String("Ivan"); //Para mostrar que se puede que hay
		// un new
		// String concatenacion = nombre + " " + otroNombre;
		// System.out.println(concatenacion);

		// Ejemplo 4 - for (Sumatoria de numeros de 1 a 5)
		// if
		// estructuras de control
		/*
		 * int sumatoria = 0; for (int i = 0; i<= 5; i++) { sumatoria = sumatoria + i; }
		 * System.out.println(sumatoria);
		 * 
		 * //Quiero que me digan si la sumatoria es par o impar if( sumatoria % 2 == 0)
		 * { System.out.println("Es par"); } else { System.out.println("Es impar"); }
		 * 
		 * //Otra alternativa System.out.println(( sumatoria % 2 == 0) ? "Es par" :
		 * "Es Impar");
		 */

		// Ejemplo 5 - while
		int valor = 800;
		// mientras el valor sea par lo divida por dos
		// Lo va a dividir sucesivamente por dos hasta que sea impar
		while (valor % 2 != 0) {
			valor = valor % 2;
		}
		System.out.println(valor);

	}

}
