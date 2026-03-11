package org.indra.aspect;

import java.lang.reflect.Method;

import org.springframework.aop.*;

public class BeforeServiceInvocationAspect implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println(String.format("Se invoca al metodo %s de la clase %s ", 
				method.getName(), 
				target.getClass().getSimpleName()));
		
	}

}
