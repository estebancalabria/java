package org.indra.claseOcho.models;

import java.text.MessageFormat;
import java.time.LocalDate;

import lombok.*;

@ToString
public class Car extends BusinessObject {

	@Getter @Setter
	private String model;
	@Getter @Setter
	private int year;
	@Getter @Setter
	private String color;
	
	/*@Getter @Setter
	private String fuelType;
	...*/
	
	@Override
	public void validate() throws ModelValidationException {
		// TODO Auto-generated method stub
		super.validate();
		
		if ((model==null) || (model.trim().length()==0)){
			throw new ModelValidationException("model", "Attribute cannot be empty");
		}
		
		//año entre el 1980 y actual
		int currentYear = LocalDate.now().getYear();
		if ((year <= 1980) || (year > currentYear)) {
            throw new ModelValidationException("year", MessageFormat.format("Range error. Min {0} Max {1}.",1980, currentYear));
        }
		
		//color no vacio
		if ((color == null) || color.trim().isEmpty()) {
            throw new ModelValidationException("color", "Attribute cannot be empty");
        }
	}
}
