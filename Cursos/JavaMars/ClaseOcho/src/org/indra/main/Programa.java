package org.indra.main;

import java.time.*;
import java.util.*;

import org.indra.models.Pelicula;
import org.indra.repository.*;
import org.indra.services.PeliculaService;

public class Programa {

	public static void main(String[] args) throws Exception {
		//Modo Configuracion
		//Aca se configura toda nuestra app y sus dependencias
		//Aca Definimos quien depende de quien
		//PeliculaRepositoryFactory.configureClass(PeliculaMockRepoFactory.class);
		//PeliculaRepositoryFactory.configureClass(PeliculaDbRepoFactory.class);
		
		//Modo Ejecucion
		//Una vez que tengo todo configurado ejecuto mi aplicacion normalmente
		/*PeliculaService service = new PeliculaService();
		Pelicula p = new Pelicula();
		p.setTitulo("El señor de los anillos");
		p.setDirector("Peter Jackson");
		p.setDuracion(260);
		p.setFechaSalida(LocalDate.now());
		service.add(p);*/
		
		System.out.println("Hora Actual Argentina " + LocalTime.now(ZoneId.of("America/Argentina/Salta")));
		System.out.println("Hora Actual Spain " + LocalTime.now(ZoneId.of("Europe/Madrid")));

		
		//Set<String> zonas = ZoneId.getAvailableZoneIds();
		//zonas.stream().filter(z -> z.startsWith("Europe")).forEach( z-> System.out.println(z));
	}

}
