package org.indra.test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;


class JavaUtilTests {

	@Test
	void testArrayList() {
		List<String> lista = new ArrayList<String>();
		lista.add("Esteban");
		lista.add("Carlos");
		lista.add("Irene");
		assertEquals(3, lista.size());
	}

	@Test
	void testLinkedList() {
		Collection<String> lista = new LinkedList<String>();
		lista.add("Esteban");
		lista.add("Carlos");
		lista.add("Irene");
		assertEquals(3, lista.size());
	}
	
	@Test
	void testQueue() {
		Queue<String> cola = new LinkedList<String>();
		cola.add("Uno");
		cola.add("Dos");
		cola.add("Tres");
		String elem = cola.remove();
		assertEquals("Uno", elem);
		
	}
	
	@Test
	void testStack() {
		Stack<String> pila = new Stack<String>();
		pila.add("Uno");
		pila.add("Dos");
		pila.add("Tres");
		String elem = pila.pop();
		assertEquals("Tres", elem);
		
	}
	
	@Test
	void testDictionary() {
		Dictionary<Integer, String> diccionario = new Hashtable<Integer,String>();
		diccionario.put(1, "Uno");
		diccionario.put(2, "Dos");
		diccionario.put(3, "Tres");
		assertEquals("Uno", diccionario.get(1));
		assertEquals("Dos", diccionario.get(2));
		assertEquals("Tres", diccionario.get(3));
	}
	
	@Test
	void testSet() {
		Set<String> conjunto = new TreeSet<String>();
		//Set<String> conjunto = new HashSet<String>();
		conjunto.add("a");
		conjunto.add("a");
		conjunto.add("a");
		conjunto.add("b");
		conjunto.add("b");
		conjunto.add("b");
		assertEquals(2,conjunto.size());
	}
	
	void testDate() {
		Date fecha = new Date(2022,1,1);
		assertEquals(2022,fecha.getYear());	
	}
	
	@Test
	void testCalendar() {
		Calendar fecha = new GregorianCalendar(2020,1,1);
		assertEquals(2020,fecha.get(Calendar.YEAR));
	}	
	
	@Test
	void testUUID() {
		// 0xFFFFFFFF00000000
		assertNotEquals(UUID.randomUUID(), UUID.randomUUID());
	}
	
	@Test
	void testRegex() {
		assertEquals(true, Pattern.matches("[0-9]*", "1233343"));
		assertNotEquals(true, Pattern.matches("[0-9]*", "1233343A"));
	}
}
