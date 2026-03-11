package org.indra.ClaseCatorce.controllers;

import org.indra.ClaseCatorce.models.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class EmpleadoController {
	List<Empleado> empleados = new ArrayList<Empleado>();

	public EmpleadoController() {
		empleados.add(new Empleado(1,"Ernesto", "Martinez"));
		empleados.add(new Empleado(2,"Daniel", "Ventura"));
		empleados.add(new Empleado(3,"Raquel", "Riello"));
		empleados.add(new Empleado(4,"Esteban", "Calabria"));
	}
	
	@GetMapping("/api/empleado")
	public List<Empleado> getAll(){
		return empleados;
	}
	
	@GetMapping("/api/empleado/{id}")
	public Empleado getById(@PathVariable(name = "id")int id) {
		return empleados.stream().filter(e -> e.getId() == id).findFirst().get();
	}
	
	@GetMapping("/api/empleado/echo")
	public Empleado echoEmpleado(Empleado empleado){
		//http://localhost:8080/api/empleado/echo?nombre=Esteban&apellido=Calabria&id=1000
		return empleado;
	}
	
	@PostMapping("/api/empleado/add")
	public Empleado addEmpleado(@RequestBody Empleado empleado) {
		return empleado;
	}
}
