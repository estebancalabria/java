package org.indra.claseUno;

import java.text.MessageFormat;
/*import java.util.ArrayList;
import java.util.List;
import java.util.Random;*/
import java.util.*;

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
		/*
		 * int valor = 800; // mientras el valor sea par lo divida por dos // Lo va a
		 * dividir sucesivamente por dos hasta que sea impar while (valor % 2 != 0) {
		 * valor = valor % 2; } System.out.println(valor);
		 */

		// Ejemplo 6 - Listas
		/*
		 * List<String> nombres = new ArrayList<String>(); nombres.add("Ruben");
		 * nombres.add("Ivan"); nombres.add("Jiten"); nombres.add("Jose");
		 * nombres.add("Jorge");
		 * 
		 * System.out.println("En la lista tengo " + nombres.size());
		 * System.out.println("Los nombres que tengo son:"); for (String nombre :
		 * nombres) { System.out.println(nombre); }
		 * 
		 * //Creo que esta manera es mas anti natural pero se puede
		 * System.out.println("----"); for (int i=0; i<nombres.size(); i++) {
		 * System.out.println(nombres.get(i)); }
		 * 
		 * System.out.println("----"); //Quiero iterar la lista pero mostrar los nombres
		 * en mayuscula for (String nombre: nombres) {
		 * System.out.println(nombre.toUpperCase()); }
		 */

		// Ejemplo 7 - Array
		// String[] nombres = new String[3];
		// nombres[0] ="Esteban";
		// nombres[1] ="Alvaro";
		// nombres[2] ="Cova";
		// nombres[3] ="Otro"; //Error

		/*
		 * String[] nombres = {"Esteban","Alvaro","Cova"};
		 * 
		 * for (String nombre:nombres) { System.out.println(nombre); }
		 */

		// Ejemplo 8 - Poner en practica lo que vimos
		/*Random generadorNumerosAzar = new Random();
		System.out.println(generadorNumerosAzar.nextInt(1, 100));

		// 8.1 Generar una lista de 100 numeros al azar entre 1 y 100
		List<Integer> numeros = new ArrayList<Integer>();
		//List<int> l; Los genericos son de tipo de datos objeto

		for (int i = 0; i < 100; i++) {
			numeros.add(generadorNumerosAzar.nextInt(1, 100));
		}

		// 8.2. Mostrar la sumatoria de todos los elementos de la lista
		/*
		  int contador = 0; for (int i=0; i<100; i++) { 
		  contador += numeros.get(i); 
		  }
		  System.out.println(contador);
		 */

		/*Integer sumatoria = 0;
		for (Integer num : numeros) {
			sumatoria += numeros.get(num);
		}
		System.out.println("La sumatoria de elementos es " + sumatoria);
		
		//8.3. Mostrar el promedio de los elementos del arreglo
		int promedioEntero = sumatoria / numeros.size();
		System.out.println("El promedio Entero es " + promedioEntero);
		double promedioDouble = (double)sumatoria / numeros.size();
		System.out.println("El promedio double es " + promedioDouble);
		
		//8.4. Mostrar el mayor elemento de la lista
		int maxElem = 0;
        for (Integer num : numeros) {
            if(maxElem < num) {
                maxElem = num;
            }
        }
        System.out.println("El mayor de la lista es " + maxElem);
        System.out.println("El mayor de la lista es " + Collections.max(numeros)); //QUE GRANDE!!
        
        //8.5 - Expresion lanbda (Vamos a ver mas adelante)
        numeros.forEach( System.out::println );
        numeros.forEach( n -> System.out.println(n));
        
        //Como seria entonces calculr la sumatoria con una expresion lambda con un foreach
		/*int sumatoria = 0;
		numeros.stream();
		System.out.println("La sumatoria de elementos es " + sumatoria);*/
        
		//Ejemplo 9 - Stack vs heap
        /*int a = 10;
        int b = a;
        
        Entero e = new Entero();
        e.valor = 20;
        
        
        
        Entero e2 = e;   //Copia las refencias
        e2.valor = 30;
        System.out.println(e.valor);
        
        Entero copia = new Entero();
        //copia = e2; //Copio la referencia
        copia.valor = e2.valor;  //Si quiero una copia
        
        String cadena = "Hola que tal";  //No hice new, pero igual va en el heap
        String cadena2 = cadena;  //Copio las referencias
        cadena2 = "Pepe";  //Creo una nueva cadena en el heap (sin hacer new)
        
        String c1 = "Hola";
        String c2 = "Hola";  //Aca java tramposillo los hizo apuntar al mismo lugar
        
        c2 = "Pepe";
        c1 = "Hola";
        c2 = "Hola";
        
        if (c1 == c2) { //Compara las dos referencias  
        	System.out.println("Son Iguales");
        }else {
        	System.out.println("Son Diferentes");
        }
        
        c2 = c2.toUpperCase();
        c1 = c1.toLowerCase().toUpperCase();
        
        if (c1 == c2) { //Compara las dos referencias  
        	System.out.println("Son Iguales");
        }else {
        	System.out.println("Son Diferentes");
        }
        
        if (c1.equals(c2)) { //Compara las dos referencias  
        	System.out.println("Son Iguales");
        }else {
        	System.out.println("Son Diferentes");
        }*/
		
		//Ejemplo 10 - Concatenar Strings
		//Ejemplo malardo
		/*String cadena = "Hola";
		cadena += " como";
		cadena += " estas";
		cadena += " espero";
		cadena += " que";
		cadena += " bien";
		//Cree 6 objetos en el heap
		for (int i=1; i<100; i++) {
			cadena += i;
		}
		System.out.println(cadena);
		//malardo porque creo muchos objetos en heap que no uso
		//EVITAR UN LOOP GRANDE QUE CONCATENE STRINGS
		
		//Ejemplo buenardo
		StringBuilder builder = new StringBuilder();
		builder.append("Hola");
		builder.append(" como");
		builder.append(" estas");
		builder.append(" espero");
		builder.append(" que");
		builder.append(" bien");
		for (int i=1; i<100; i++) {
			builder.append(i);
		}
		System.out.println(builder.toString());
		//Este codigo ahora esta optimizado y tengo 2 objetos solos en el heap
		*/
		
		//Ejemplo 11 - Interaccion con el usuario
		Scanner scanner = new Scanner(System.in);
		System.out.println("Como te llamas?");
		String nombre = scanner.nextLine();
		//Malardo
		//System.out.println("Bienvenido " + nombre + " a java interactivo");
		//Mejorarlo
		//System.out.println(String.format("Bienvenido %s a java interactivo", nombre));
		//"Mas mejor"
		//System.out.format("Bienvenido %s a java interactivo", nombre);
		//Al estilo C#
		System.out.println(MessageFormat.format("Bienvenido {0} a java interactivo", nombre));
		scanner.close();
	}

}
