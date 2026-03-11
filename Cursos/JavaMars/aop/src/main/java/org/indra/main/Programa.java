package org.indra.main;

import org.indra.services.HolaMundoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Programa {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		try (ClassPathXmlApplicationContext  contexto =new ClassPathXmlApplicationContext("beans.xml")){ 
	        
		    HolaMundoService service = (HolaMundoService)contexto.getBean("serviceProxy");
		    
		    service.demo2(100);
		   
	        //System.out.println(service.demo());
		}
	}

}
