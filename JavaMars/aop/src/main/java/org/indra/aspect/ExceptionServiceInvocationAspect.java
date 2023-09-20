package org.indra.aspect;

import org.springframework.aop.ThrowsAdvice;

public class ExceptionServiceInvocationAspect implements ThrowsAdvice {
 
	public void afterThrowing(Exception ex) {
		System.out.println("Se lanzo una excepcion");
	}
}
