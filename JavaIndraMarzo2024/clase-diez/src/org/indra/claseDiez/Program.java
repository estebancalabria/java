package org.indra.claseDiez;

import org.springframework.beans.*;
import org.springframework.context.*;
import org.springframework.context.support.*;


public class Program {
	
	public static void main(String[] args) throws BeansException  {

		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
		
		Repository repo = (Repository)context.getBean("repository");
		
		repo.save();
		

	}

}
