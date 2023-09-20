package org.indra.clasequince.controllers;

import org.indra.clasequince.dto.*;
import org.indra.clasequince.models.Cancion;
import org.indra.clasequince.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class CancionController extends BaseController{
	
	@Autowired
	private ICancionService cancionService;
	
	@PostMapping("/api/cancion")
	public Object nuevaCancion(@RequestBody Cancion cancion) {
		try {
			
			cancionService.add(cancion);
			
			return new Exito(cancion);
			
		}catch (Exception exception){
			return new RespuestaError(exception.getMessage());
		}
	}
	
	/*public Response respuesta() {
		
	}*/

}
