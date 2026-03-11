package org.indra.clasequince;

import java.util.List;

import org.indra.clasequince.models.Cancion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClasequinceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClasequinceApplication.class, args);
		List<Cancion> lista;
		
		lista.stream().mapToInt(c->c.getId()).max().getAsInt();

}
