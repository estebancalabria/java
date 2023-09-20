package org.allianz.hellospringboot.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAfterGetMethodServiceAspect {
	
	private Logger logger = LoggerFactory.getLogger(LogAfterGetMethodServiceAspect.class);
	
	//Dentro de cuaquier paquete que comiende con org.allianz.hellospringboot
	//Cualquier clase
	//Todos los metodos que comienzan con get
	@AfterReturning(value = "execution(* org.allianz.hellospringboot.*.*.get*(..))", 
			returning = "returnValue")
	public void afterGet(JoinPoint metodo, Object returnValue) {
		logger.info("AFTER : ASPECT : Ejecutando el metodo get " + metodo.getSignature());
		logger.info("AFTER : ASPECT : El metodo devuelve " + returnValue);
	}
}
