package org.indra.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.indra.ejercicios.ContadorPalabras;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

class DemoTests {

	@Test
	void testContadorPalabra() {
		ContadorPalabras contador = new ContadorPalabras("la casa de la esquina");
		assertEquals(2, contador.getOcurrencias("la"));
	}
	
	@Test
	void testFilter() {
		List<String> lista = Arrays.asList("Hola","Chau","MasLarga");
		/*new ArrayList() {{
			add("Hola");
			add("Chau")
		}};*/
		long masLargasQue5 = lista.stream().filter(p -> p.length()>5).count();
		assertEquals(1, masLargasQue5);
	}

	@Test
	void testMap() {
		List<String> lista = Arrays.asList("casa","pera","arbol","rojo");
		List<String> mayuscula = lista.stream().map(p -> p.toUpperCase()).collect(Collectors.toList());
		assertEquals("CASA", mayuscula.get(0));
	}
	
	@Test
	void testSum() {
		List<Integer> numeros = Arrays.asList(1,2,3,4,5);
		int sumatoria = numeros.stream().reduce(0, (acum,actual)-> acum+actual);
		assertEquals(15, sumatoria);
	}
	
	@Test
    void testContiene() {
        List<String> lista = Arrays.asList("hola","chao","adios", "Esteban");
        boolean contiene = lista.stream().anyMatch(p -> p.equals("Esteban"));
        assertEquals(true,contiene);
    }
	
	@Test
	@Order(1)
    void testArchivoNombres() throws IOException{
        File archivo = new File("nombres.txt");
        archivo.createNewFile();
        FileWriter writer = new FileWriter("nombres.txt");
        writer.write("Juan\n");
        writer.write("Pepe\n");
        writer.write("Fernando\n");
        writer.write("Carlos\n");
        writer.write("Elena\n");
        writer.write("Elena2\n");
        writer.write("Elena3\n");
        writer.close();
        assertEquals(true, archivo.exists());   
    }
	
	@Test
	@Order(2)
	void testReadFile() throws IOException {
        try (Stream<String> lineas = Files.lines(Path.of("nombres.txt"))) {
			long n = lineas.filter(p -> p.length()>4).count();
			assertEquals(5, n);
		}
    }	
}
