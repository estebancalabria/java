package org.indra.aspect;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class AfterServiceInvocationAspect implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		
		System.out.println(String.format("Se invoca al metodo %s de la clase %s y devuelve %s ", 
				method.getName(), 
				target.getClass().getSimpleName(),
				returnValue));
		
	}

}
