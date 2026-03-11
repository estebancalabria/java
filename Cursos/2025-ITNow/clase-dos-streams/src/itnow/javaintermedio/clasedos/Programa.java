package itnow.javaintermedio.clasedos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Programa {

	public static void main(String[] args) {
		System.out.println("Bienvenido al uso de streams en java.");
		System.out.println("Una nueva forma de recorrer colecciones con expresiones lambda.");

		// Ejemplo 1: Foreach lambda
		List<String> nombres = List.of("Xavier", "Guillem", "Esteban", "Cristina", "Iván", "Juan", "Laura");

		System.out.println("EJ1: Recorro una lista (for)");
		System.out.println("Voy a recorrer la lista primero con un for each convencional:");
		for (String nombre : nombres) {
			System.out.print(nombre);
			System.out.print(",");
		}
		System.out.println("");
		System.out.println("");

		System.out.println("Voy a recorrer la lista primero con un foreach con una expresion lambda:");
		System.out.println("El metodo foreach recibe una interfaz funcional de tipo Consumer<T> que devuelve void y recibe un parametro de tipo T");
		//Consumer<Integer> l;
		nombres.forEach(str -> System.out.print(str + ","));
		System.out.println("");
		System.out.println("");

		System.out.println(
				"Voy a recorrer la lista primero con un foreach con una expresion lambda y method reference (::)");
		nombres.forEach(System.out::print);
		System.out.println("");
		System.out.println("");

		// Ejemplo 2: Filtrar elementos de un array con streams
		// Vamos a quedarnos con los nombres que tienen 5 caracteres
		System.out.println("Voy a filtrar los nombres que tienen 5 caracteres o menos de forma tradicional");
		List<String> nombresCortos = new ArrayList<>();
		// Creo la nueva lista
		for (String nombre : nombres) {
			if (nombre.length() <= 5) {
				nombresCortos.add(nombre);
			}
		}
		// La Muestro
		for (String nombre : nombresCortos) {
			System.out.print(nombre);
			System.out.print(",");
		}
		System.out.println("");
		System.out.println("");

		System.out.println("Voy a filtrar los nombres que tienen 5 caracteres o menos con un filter (streams y lambda)");
		System.out.println("El metodo filter() recibe una interfaz funcional de tipo Predicate<T> que recibe un parametro de tipo T y devuelve un boolean");
		nombres.stream().filter(n -> n.length() <= 5).forEach(n -> System.out.print(n + ","));
		System.out.println("");
		System.out.println("");

		// Ejemplo 3: Convertir un array en otro
		System.out.println("Ejemplo 3");
		System.out.println("Voy a convertir un array en otro de la forma convencional");
		List<String> nombresEnMayuscula = new ArrayList<>();
		for (String nombre : nombres) {
			nombresEnMayuscula.add(nombre.toUpperCase());
		}
		System.out.println(nombresEnMayuscula);
		System.out.println("");

		System.out.println("Voy a hacer lo mismo con el map (lambda y streams)");
		System.out.println("El metodo map() recibe una interfaz funional de tipo Function<T> que recibe un parametro de tipo T y devuelve un objeto de tipo T");
		System.out.println(nombres.stream().map(n -> n.toUpperCase()).toList());
		System.out.println("");
		System.out.println("");

		// Ejemplo 4:
		System.out.println("Ejemplo 4");
		System.out.println("Quiero determinar la palabra mas larga de forma tradicional");
		String nombreLargo = "";
		int longitudMax = 0;
		for (String nombre : nombres) {
			if (nombre.length() > longitudMax) {
				nombreLargo = nombre;
				longitudMax = nombre.length();
			}
		}
		System.out.println("La palabra más larga es: " + nombreLargo);
		System.out.println("");
		
		System.out.println("Quiero determinar la palabra mas larga con reduce");
		System.out.println("El metodo reduce() recibe una interfaz funional de tipo BinaryOperator<T> que recibe dos parametros de tipo T y devuelve un objeto de tipo T");
		System.out.print("Palabra mas larga : ");
		nombres.stream().reduce((a,b)-> (a.length() > b.length() ? a : b  ) ).ifPresent( System.out:: println);
		System.out.println("");

	}

}
