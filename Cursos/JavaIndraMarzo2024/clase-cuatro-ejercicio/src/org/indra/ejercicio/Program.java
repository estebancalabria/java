package org.indra.ejercicio;

import java.util.*;

public class Program {

	public static void main(String[] args) {
		//Ejercicio contar palabras
		String entrada = "Esta es una entrada. Esta es la mejor entrada. Es la que vamos a usar para el ejericio";
		//Quiero que separen el texto en palabras
		//Luego usen una tabla de hash o diccionario de java.util
		//Para contar cuantas ocurrencias hay de cada palabra
		//Luego quiero que recorran el diccionario y muesten cuantas veces aparece cada palabra
		
		List<String> palabras = Arrays.asList(entrada.split(" "));
		HashMap<String, Integer> ocurrencias = new HashMap<String, Integer> ();  
		palabras.forEach(p ->{
			if (ocurrencias.containsKey(p)) {
				ocurrencias.put(p, ocurrencias.get(p) + 1); 
			} else {
				ocurrencias.put(p, 1);
			}
		});
		
		ocurrencias.keySet().forEach(p ->{
			System.out.format("La palabra %s aparece %d veces \n", p, ocurrencias.get(p));
		});
	}

}
