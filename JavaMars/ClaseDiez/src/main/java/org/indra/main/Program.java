package org.indra.main;

import java.io.*;

import org.indra.services.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

	public static void main(String[] args) throws IOException {
        //ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		//IGeografiaService geografiaService = (IGeografiaService) context.getBean("geografiaService");
		
		try (ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class)) {
			IGeografiaService geografiaService = context.getBean(IGeografiaService.class);
					
			geografiaService.getComunidadesAutonomas().stream().forEach(System.out::println);
			System.out.println("----");
			geografiaService.getProvincias("Andalucia").stream().forEach(System.out::println);
			
			context.registerShutdownHook();
		} 
   }
}
