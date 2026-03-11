package indra.talentCamp.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.*;

@Aspect
@Component
public class LoggingAspect {
	
	@Before(value = "execution(* indra.talentCamp.aop.controllers.AopDemoController.*(..))")
    public void logBefore(JoinPoint joinPoint) {
		
    	Logger logger = LoggerFactory.getLogger(joinPoint.getThis().getClass());
    	logger.info("Entrando al metodo " 
    			+ joinPoint.getSignature().getName()
    			+ " de la clase " 
    			+ joinPoint.getThis().getClass().getName());
    }
    
	@After(value = "execution(* indra.talentCamp.aop.controllers.AopDemoController.*(..))")
    public void logAfter(JoinPoint joinPoint) {
    	Logger logger = LoggerFactory.getLogger(joinPoint.getThis().getClass());
    	logger.info("Luego de Ejecutar el metodo " 
    			+ joinPoint.getSignature().getName()
    			+ " de la clase " 
    			+ joinPoint.getThis().getClass().getName());
    }
}
