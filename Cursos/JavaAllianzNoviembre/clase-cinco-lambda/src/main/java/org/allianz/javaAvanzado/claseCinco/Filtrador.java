package org.allianz.javaAvanzado.claseCinco;

import java.util.ArrayList;
import java.util.List;

public class Filtrador<T> {
	
	public List<T> filtrar(List<T> lista, Evaluador<T> predicado) {
		List<T> result = new ArrayList<>();
		
		for (T num : lista) {
			if (predicado.evaluar(num)) {
				result.add(num);
			}
		}
		
		return result;
	}
}
