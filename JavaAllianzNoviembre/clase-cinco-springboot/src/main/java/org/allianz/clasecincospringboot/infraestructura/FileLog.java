package org.allianz.clasecincospringboot.infraestructura;

import java.io.*;

import org.springframework.stereotype.Component;

import lombok.SneakyThrows;

@Component
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
