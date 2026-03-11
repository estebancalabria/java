package indra.talentCamp.aop.controllers;

import org.slf4j.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AopDemoController {
	
	Logger _logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value = "/api/m1")
	public ResponseEntity<?> metodo1(){
		//_logger.info("Entra al metodo m1 del conrolador AopDemoController");
		//...cuero del controllar, llamamos al servicio
		//_logger.info("Luego de ejecutar m1 del conrolador AopDemoController");
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/api/m2")
	public ResponseEntity<?> metodo2(){
		//_logger.info("Entra al metodo m2 del conrolador AopDemoController");
		//...cuero del controllar, llamamos al servicio
		//_logger.info("Luego de ejecutar m2 del conrolador AopDemoController");
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/api/m3")
	public ResponseEntity<?> metodo3(){
		//_logger.info("Entra al metodo m3 del conrolador AopDemoController");
		//...cuero del controllar, llamamos al servicio
		//_logger.info("Luego de ejecutar m3 del conrolador AopDemoController");
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/api/m4")
	public ResponseEntity<?> metodo4(){
		//_logger.info("Entra al metodo m4 del conrolador AopDemoController");
		//...cuero del controllar, llamamos al servicio
		//_logger.info("Luego de ejecutar m4 del conrolador AopDemoController");
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/api/m5")
	public ResponseEntity<?> metodo5(){
		_logger.info("Entra al metodo m5 del conrolador AopDemoController");
		//...cuero del controllar, llamamos al servicio
		_logger.info("Luego de ejecutar m5 del conrolador AopDemoController");
		return ResponseEntity.ok().build();
	}

}
