package org.itnow.cursoSpring.clasecuatro.controllers;

import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.itnow.cursoSpring.clasecuatro.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String index(Model modelo) {
		modelo.addAttribute("titulo", "Bienvenidos a mi aplicacion");
		
		List<Alumno> alumnos = new ArrayList<Alumno>();
	
		Alumno manuel = new Alumno();
		manuel.setNombre("M4nuel");
		manuel.setApellido("Vazquez Fonzalez");
		alumnos.add(manuel);
		
		Alumno javierAlejandro = new Alumno();
		javierAlejandro.setNombre("J4vier");
		javierAlejandro.setApellido("Alejandro Garcia");
		alumnos.add(javierAlejandro);
		
		Alumno javierMartinez = new Alumno();
		javierMartinez.setNombre("J4vier");
		javierMartinez.setApellido("Martinez Costantino");
		alumnos.add(javierMartinez);
		
		Alumno javierMorcillo = new Alumno();
		javierMorcillo.setNombre("J4vier");
		javierMorcillo.setApellido("Morcillo");
		alumnos.add(javierMorcillo);
		
		Alumno jonatan = new Alumno();
		jonatan.setNombre("Jonatan");
		jonatan.setApellido("Sanchez Velazquez");
		alumnos.add(jonatan);
		
		modelo.addAttribute("alumnos",alumnos);

		return "index";
	}

}
