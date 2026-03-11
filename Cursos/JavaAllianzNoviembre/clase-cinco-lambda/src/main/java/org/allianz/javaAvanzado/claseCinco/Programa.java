package org.allianz.javaAvanzado.claseCinco;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Programa {

	// Metodos Auxiliares
	public static void mostrarNumero(Integer n) {
		System.out.println(n);
	}

	// Recibe una lista y una interfaz funcional
	public static void procesarLista(List<Integer> lista, IntegerConsumer procesador) {
		for (int num : lista) {
			procesador.consumir(num);
		}
	}

	public static void mostrarLista(List<String> lista, StringConversor conversor) {
		for (String cadena : lista) {
			System.out.println(conversor.convertir(cadena));
		}
	}

	public static List<Integer> filtrarNumeros(List<Integer> numeros, EvaluadorInteger predicado) {
		List<Integer> result = new ArrayList<Integer>();

		for (int num : numeros) {
			if (predicado.evaluar(num)) {
				result.add(num);
			}
		}

		return result;
	}
	


	// Ejemplos
	public static void ejemplo1_foreach_tradicional() {
		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(1);
		numeros.add(2);
		numeros.add(3);

		for (int num : numeros) {
			System.out.println(num);
		}
	}

	public static void ejemplo2_foreach_lambda() {
		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(1);
		numeros.add(2);
		numeros.add(3);

		// Lo hacen ustedes y me lo pasan?
		numeros.forEach(num -> {
			System.out.println(num);
		});

		// Seria lomismo que escribir una funcion anonima
		// numeros.forEach(function (n){ System.out.println(n);} );
	}

	public static void ejemplo3_foreach_punteroMetodo() {
		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(1);
		numeros.add(2);
		numeros.add(3);

		// Lo hacen ustedes y me lo pasan?
		// Double Colon operator
		numeros.forEach(System.out::println);
	}

	public static void ejemplo3_foreach_punteroMetodoMio() {
		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(1);
		numeros.add(2);
		numeros.add(3);

		// Lo hacen ustedes y me lo pasan?
		numeros.forEach(Programa::mostrarNumero);
	}

	public static void ejemplo4_foreach_punteroMetodoVariable() {
		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(1);
		numeros.add(2);
		numeros.add(3);

		Consumer<Integer> mostrador = n -> {
			System.out.println(n);
		};
		// Consumer<Integer> mostrador = System.out::println;
		// Consumer<Integer> mostrador = Programa::mostrrarNumero;
		numeros.forEach(mostrador);
	}

	public static void ejemplo5_conInterfacesFuncionales() {

		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(1);
		numeros.add(2);
		numeros.add(3);

		// aca estaria "implementando" esta interfaz
		 IntegerConsumer mostrador = n -> { System.out.println(n); };
		// IntegerConsumer mostrador = System.out::println;
		// IntegerConsumer mostrador = Programa::mostrrarNumero;
	
				
		//// numeros.forEach(mostrador); //ForEach aca no funciona
		procesarLista(numeros, mostrador);
		
		procesarLista(numeros, n -> {
			System.out.println(n);
		});
	}

	public static void ejemplo6_conInterfacesFuncionales() {
		List<String> nombres = new ArrayList<String>();
		nombres.add("jordi");
		nombres.add("javier");
		nombres.add("wendy");
		nombres.add("laura");

		// Con Parentesis
		mostrarLista(nombres, n -> (n.toUpperCase()));
		// Con llaves
		mostrarLista(nombres, n -> {
			return n.toUpperCase();
		});
		// O bien...
		StringConversor conversor = n -> (n.toUpperCase());
		mostrarLista(nombres, conversor);
	}
	
	public static void ejemplo7_conInterfacesFuncionales() {

		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(1);
		numeros.add(2);
		numeros.add(3);
		numeros.add(4);
		numeros.add(5);
		
		
		
		//numeros = filtrarNumeros(numeros, n -> (n%2==0));
		
		//o bien...
		EvaluadorInteger pares = n -> (n%2==0);
		numeros = filtrarNumeros(numeros, pares);
		
	    procesarLista(numeros, System.out::println);
	}
	
	public static void ejemplo8_conInterfacesFuncionales_Generico() {
		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(1);numeros.add(2);numeros.add(3);numeros.add(4);
		
		//Me quedo con los numeros pares
		Filtrador<Integer> filtrador = new Filtrador<>();
		numeros = filtrador.filtrar(numeros, n -> (n%2==0));
		procesarLista(numeros, System.out::println);
		
		//Me quedo con los string que miden menos de 5 caracteres (chau Javier)
		List<String> nombres = new ArrayList<String>();
		nombres.add("jordi");
		nombres.add("javier");
		nombres.add("wendy");
		nombres.add("laura");
		Filtrador<String> filtradorNombres = new Filtrador<>();
		filtradorNombres.filtrar(nombres, n -> (n.length() <= 5));
		nombres.forEach(System.out::println);
	}
	
	public static void ejemplo9_conLoQueYaEstaEnJava() {
		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(1);numeros.add(2);numeros.add(3);numeros.add(4);
		
		//Me quedo con los numeros pares
		//numeros = numeros.stream().filter(n -> (n%2==0)).toList();
		numeros = numeros.stream().filter(n -> (n%2==0)).collect(Collectors.toList());
		procesarLista(numeros, System.out::println);
		
		//Me quedo con los string que miden menos de 5 caracteres (chau Javier)
		List<String> nombres = new ArrayList<String>();
		nombres.add("jordi");
		nombres.add("javier");
		nombres.add("wendy");
		nombres.add("laura");
		//nombres.stream().filter(n -> (n.length() <= 5)).toList();
		nombres.stream().filter(n -> (n.length() <= 5)).collect(Collectors.toList());
		nombres.forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ejemplo6_conInterfacesFuncionales();
	}

}
