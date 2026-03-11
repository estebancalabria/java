package org.indra.lambda;

//Interfaz funcional generica (1 solo metodo)
public interface Seleccionador<T> {
	boolean elegir(T obj);
}
