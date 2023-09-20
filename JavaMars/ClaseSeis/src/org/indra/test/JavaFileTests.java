package org.indra.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class JavaFileTests {

	@Test
	void testCreateNewFile() throws IOException {
		File archivo = new File("text.txt");
		archivo.createNewFile();
		assertEquals(true, archivo.exists());
	}
	
	@Test
	void testWriteFile() throws IOException {
		FileWriter writer = new FileWriter("text.txt");
		writer.write("Este es un texto");
		writer.close();
		
		File archivo = new File("text.txt");
		Scanner reader = new Scanner(archivo);
		String linea = "";
		if (reader.hasNextLine()){
			linea =  reader.nextLine();
		}
		
		assertEquals("Este es un texto", linea);
		
		reader.close();
	}

}
