package org.indra.claseTres;

import java.util.*;
import java.util.stream.*;

import org.indra.claseTres.models.*;

public class Program {

	public static void main(String[] args) {
		System.out.println("Bienvenidos a la clase tres");

		System.out.println("Sistemaa para una universidad");

		// Quienes asisten a nuestra universidad?

		// Version 1
		/*
		 * List<Estudiante> curso = new ArrayList<Estudiante>(); curso.add(new
		 * Estudiante("Jiten", "Parwani")); curso.add(new Estudiante("Alvaro",
		 * "Orejon")); curso.add(new Estudiante("Ruben", "Gonzalez"));
		 */

		// Encuentro en mi codigo 2 problemas
		// 1. No puedo agregar profesores a mi curso
		// curso.add(new Profesor(123,"Esteban","Calabria")); //me da error
		// 2. Veo que hay codigo duplicado entre el Estudiante y el Profesor

		// Version 2
		/*
		 * List<Persona> curso = new ArrayList<Persona>(); curso.add(new
		 * Profesor(1234,"Esteban", "Calabria")); curso.add(new Estudiante("Jiten",
		 * "Parwani")); curso.add(new Estudiante("Alvaro", "Orejon")); curso.add(new
		 * Estudiante("Ruben", "Gonzalez"));
		 */

		// Version 3 - Incializacion Doble Llavecita
		/*
		 * List<Persona> curso = new ArrayList<Persona>() {{ add(new
		 * Profesor(1234,"Esteban", "Calabria")); add(new Estudiante("Jiten",
		 * "Parwani")); add(new Estudiante("Alvaro", "Orejon")); add(new
		 * Estudiante("Ruben", "Gonzalez")); }};
		 */

		// Version 4 - (Java 9 : Inicializacion con Factory Mehods)
		/*
		 * List<Persona> curso = List.of( new Profesor(1234,"Esteban", "Calabria"), new
		 * Estudiante("Jiten", "Parwani"), new Estudiante("Alvaro", "Orejon"), new
		 * Estudiante("Ruben", "Gonzalez"));
		 * 
		 * //Si agrego un elemento Tira una excepcion porque //con el List.of se crea
		 * una lista inmutable //curso.add(new Estudiante("Prueba","Prueba"));
		 */

		// Version 5 - Streams (Java 8) - Para ir introduciendo los streams - Expresiones 
		/*List<Persona> curso = Stream.of(new Profesor(1234, "Esteban", "Calabria"),
				new Estudiante("Jiten", "Parwani"),
				new Estudiante("Alvaro", "Orejon"), 
				new Estudiante("Ruben", "Gonzalez")).collect(Collectors.toList());*/
		
		//Version 6 - Con el factory method de los arrays (Convertir un array a una lista
		List<Persona> curso = Arrays.asList( new Profesor(1234,"Esteban", "Calabria"), 
				new Estudiante("Jiten", "Parwani"), 
				new Estudiante("Alvaro", "Orejon"),
				new Estudiante("Alvaro", "Monteagudo"), 
	        	new Estudiante("Ruben", "Gonzalez"));

		System.out.println("El curso esta compuesto por...");
		curso.forEach(a -> a.saludar()); // Polimorfismo
		// curso.forEach(Persona::saludar);
		
		//Que pasa ahora si quiero listar solamente los estudiantes?
		//operador instanceof
		System.out.println();
		System.out.println("Los estudiantes son:");
		
		//Version 1 : For convencional
		/*for (Persona p : curso) {
			if (p instanceof Estudiante) {
				p.saludar();
			}
		}*/
		
		//Version 2 : Con expresiones lambda
		curso.stream().filter(p -> p instanceof Estudiante).forEach(Persona::saludar);
		
		//Ejemplo para que hagan ustedes....
		System.out.println();
		System.out.println("Los que comienzan con la letra A");
		//Propuesta de Ivan : Bien!
		curso.stream().filter(p -> p.getNombre().startsWith("A")).forEach(Persona::saludar);
		//Otras opciones
		/*curso.stream().filter(p -> (p.getNombre().startsWith("A")) && (p instanceof Profesor)).forEach(Persona::saludar);
		curso.stream()
		  .filter(p -> (p.getNombre().startsWith("A")))
		  .filter(p -> p instanceof Profesor).forEach(Persona::saludar);*/
		
		//Saber de que tipo son los objetos
		curso.stream().forEach(p -> System.out.println(p.getClass().getName()));
		
		System.out.println(curso.getClass().getSimpleName());
		
		List<Integer> numeros  = List.of(4,5,5,6,7,8);
		System.out.println(numeros.getClass().getName());
	}

}
