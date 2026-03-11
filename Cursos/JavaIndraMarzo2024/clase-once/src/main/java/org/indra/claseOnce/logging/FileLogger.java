package org.indra.claseOnce.logging;

import java.io.*;

import lombok.SneakyThrows;

public class FileLogger implements Logger {

	private String fileName;

	public FileLogger(String fileName) {
		super();
		this.fileName = fileName;
	}

	
	@Override
	@SneakyThrows
	public void log(String mensaje) {
        try (PrintWriter out = new PrintWriter(this.fileName+".txt")) {
			out.println("mensaje");
		}		
	}
}
