package org.indra.ejercicios;

import java.util.*;
import java.util.stream.*;

public class ContadorPalabras {
	
	public Map<String, Integer> ocurrencias= new Hashtable<String,Integer>();

	public ContadorPalabras(String cadena) {
		String[] palabras = cadena.split(" ");
		Stream<String> stream = Arrays.stream(palabras);
		stream.forEach(palabra ->{
			if (!this.ocurrencias.containsKey(palabra)) {
				ocurrencias.put(palabra, 1);
			}else {
				ocurrencias.put(palabra, this.ocurrencias.get(palabra)+1);
			}
		});
	}
	
	public int getOcurrencias(String palabra) {
		int result = 0;
		if (this.ocurrencias.containsKey(palabra)){
			result = ocurrencias.get(palabra);
		}
		return result;
	}
}
