package org.indra.aopspringboot.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HelloAspect {
   @Pointcut("execution(* org.indra.aopspringboot.services.HelloAspectService.*())")
   public void beforeServiceExecutes() {}
   
   @After("beforeServiceExecutes()")//applying pointcut on before advice  
   public void myadvice(JoinPoint jp)//it is advice (before advice)  
   {  
       System.out.println("Antes de Ejecutar el servicio " + jp.getSignature());  
       //System.out.println("Method Signature: "  + jp.getSignature());  
   }  
}
