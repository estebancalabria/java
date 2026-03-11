package org.allianz.javaAvanzado.claseUno.infraestructura;

import java.io.*;

import lombok.SneakyThrows;

public class FileLog implements Log {

	@Override
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

	@Override
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
