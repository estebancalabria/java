package org.indra.mockitodemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import org.indra.mockitodemo.models.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import java.util.*;


@RunWith(BlockJUnit4ClassRunner.class) //Para spring
public class AppTest 
{
	@Test
	public void testWhenThenReturn() {
		//configuro el mock
		List<String> lista = (List<String>)mock(List.class);
		//La lista tiene que tener 20 elementos
		when(lista.size()).thenReturn(20);
		
		//Test
		int size = lista.size();
		assertEquals(20, size);
	}
	
	
	@Test
	public void testVerifyMethodInvocation() {
		//configuro el mock
		List<String> lista = (List<String>)mock(List.class);
		lista.add("Hola");
		System.out.println(lista.size()); //<<< Me va a dar 0 por mas que le agregue un elemento
		
		//Verifico que se haya llamado al add con el parametro "hola"
		Mockito.verify(lista).add("Hola");
	}
	
	@Test
	public void testHombreQueCalcula() {
		ICalculadora calculadora = mock(ICalculadora.class);
		when(calculadora.sumar(2, 3)).thenReturn(5);
		
		ElHombreQueCalculaba beremis = new ElHombreQueCalculaba(calculadora);
		int result = beremis.sumarVariasVeces(2, 3, 2);
		assertEquals(10, result);
	}
}
