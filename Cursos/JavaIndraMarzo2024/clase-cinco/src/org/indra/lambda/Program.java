package org.indra.lambda;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Program {
	
	//El mundo antes de las expresiones lambda
	public static void mostrarMasViejosQue(List<Persona> personas, int edadMinima) {
		for (Persona p : personas) {
			if (p.getEdad()>edadMinima) {
				System.out.println(p.getNombre());
			}
		}
		
	}
	
	public static void mostrarPersonasFiltradas(List<Persona> personas, SeleccionadorPersona seleccionador) {
		for (Persona p : personas) {
			if (seleccionador.elegir(p)) {
				System.out.println(p.getNombre());
			}
		}
	}
	
	public static<T> void  mostarListaFiltrada(List<T> lista, Seleccionador<T> seleccionador) {
		for (T p : lista) {
			if (seleccionador.elegir(p)) {
				System.out.println(p);
			}
		}
	}
	
	public static boolean autoRapido(Automovil a) {
		return a.getVelocidad() > 15;
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Accion saludar = () -> {System.out.println("Hola");};
		Accion despedirse = () -> {System.out.println("Chau");};
		
		saludar.ejecutar();
		despedirse.ejecutar();
		
		OperacionBinaria suma = (a,b) -> (a+b); //Con parentesis return impllicito
		OperacionBinaria resta= (a,b) -> {return a-b;}; //con llaves escribo el return 
		
		System.out.println(suma.ejecutar(2,2));
		
		List<Persona> personas = List.of(
				new Persona("Juan", 21),
				new Persona("Ana", 34),
				new Persona("Bob", 44),
				new Persona("Carlos", 44),
				new Persona("Damian", 21),
				new Persona("Esteban", 25),
				new Persona("Facundo", 61),
				new Persona("Gaston", 72),
				new Persona("Horacio", 62),
				new Persona("Ivan", 21)
				);
		mostrarMasViejosQue(personas, 30);
		System.out.println();
		mostrarPersonasFiltradas(personas, new SeleccionadorMayoresDe30());
		System.out.println();
		mostrarPersonasFiltradas(personas, p->(p.getEdad()>30));
		System.out.println();
		//SAM : Single Abstract Method
		mostrarPersonasFiltradas(personas, new SeleccionadorPersona() {
			
			@Override
			public boolean elegir(Persona p) {
				// TODO Auto-generated method stub
				return p.getEdad() > 30;
			}
		});
		System.out.println();
		personas.stream().filter(p->(p.getEdad()>30)).forEach(System.out::println); //Operador :: es puntero a metodo
		personas.stream().filter(p->(p.getEdad()>30)).forEach(p-> {System.out.println(p);});
		
		System.out.println();
		mostarListaFiltrada(personas, (p)->(p.getEdad()>30));
		System.out.println();
		mostarListaFiltrada(List.of(new Automovil(10),new Automovil(30),new Automovil(20)),
				(a)->(a.getVelocidad()>15));
		System.out.println();
		mostarListaFiltrada(List.of(new Automovil(10),new Automovil(30),new Automovil(20)),
				Program::autoRapido);
		
		//MAP
		List<Persona> clones = personas.stream().map((p)->(
				new Persona("Clon de " + p.getNombre(), p.getEdad() ))).toList();
	   
		//No me acuerdo la sintaxis para crear clases anonimas y si se podia en java (me suena en c#
		/*List<Cosa> otraCosa = personas.stream().map((p)->(new Cosa() { 
	    	int getN() {
	    		return p.getNombre();
	    	}
	    })
	    ).toList();*/
		
		//Reduce
		List<Integer> numeros = List.of(3,4,5,6,2,3,4,5);
		//Calculando la sumatoria con el reduce
		int sumatoria = numeros.stream().reduce(0, (subtotal, elemento) -> (elemento+subtotal));
		System.out.println(sumatoria);
		sumatoria = numeros.stream().mapToInt(i->i).sum();
		//Usar el reduce para encontrar el numero mas grande
		int mayor = numeros.stream().reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b);
		
		//Persona mas alta con reduce
		Persona masLongeva = personas.stream().reduce(personas.stream().findFirst().get(), 
				(p1,p2)->(p1.getEdad() > p2.getEdad()? p1 : p2));
		
		//Se acuerdan del ejercicio de contar palabras?
		String texto = "Estas son estas palabras palabras son estas estas son";
		List<String> palabras = Arrays.asList(texto.split(" "));
		Map<String, Long> ocurrencias = palabras.stream().collect(
				Collectors.groupingBy(
						Function.identity(), Collectors.counting())
				);
		ocurrencias.keySet().forEach(k -> {System.out.format("%s %d \n", k, ocurrencias.get(k));});
		
	}

}
