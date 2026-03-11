package org.allianz.hellospringboot.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogBeforeServiceAspect {

	private Logger logger = LoggerFactory.getLogger(LogBeforeServiceAspect.class);
	
	//Se ejecuta para todos los metodos del paquete org.allianz.hellospringboot.services
	//De las clases que terminan con la palabra Service
	//En todos los metodos
	//"Todos los servicios"
	@Before("execution(* org.allianz.hellospringboot.services.*Service.*(..))")
	public void antesDeInvocarServicio(JoinPoint joinPoint) {
		logger.info("ASPECTO : Invocando al metodo " + joinPoint.getSignature());
		for (Object arg : joinPoint.getArgs()) {
			logger.info("Parametro : " + arg);
		}
	}
}
