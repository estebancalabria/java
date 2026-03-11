package org.inda.main;

import java.io.FileWriter;

import lombok.*;

@Builder
@ToString
public class Reloj {
	private @NonNull String marca;
	private @NonNull String modelo;
	
	@SneakyThrows
	public void salvar() {
		val nombreArchivo = "reloj.txt";
		@Cleanup FileWriter writer = new FileWriter(nombreArchivo);
		writer.write(this.toString());
	}
}
