package org.allianz.javaAvanzado.claseCuatro.infraestructura;

import java.io.*;

import lombok.SneakyThrows;

public class FileLog implements Log {

	public void info(String mensaje)  {		
		try 
		{
			File f = new File("log.txt");
			FileWriter writer = new FileWriter(f, true);
			try {
				writer.append(mensaje + "\n");
			}finally {
				writer.close();
			}
		}catch(Exception ex) {
			throw new Error(ex);
		}
	}

	@SneakyThrows
	public void error(String mensaje) {
		File f = new File("error.txt");
		FileWriter writer = new FileWriter(f, true);
		try {
			writer.append(mensaje + "\n");
		}finally {
			writer.close();
		}		
	}

}
