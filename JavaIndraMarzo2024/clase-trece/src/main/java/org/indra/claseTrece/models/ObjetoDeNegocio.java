package org.indra.claseTrece.models;

import java.lang.reflect.Field;

import org.indra.claseTrece.models.annotations.*;

//Layer Supertype
public abstract class ObjetoDeNegocio<T> {

	private T id;

	public T getId() {
		return this.id;
	}

	public void setId(T value) {
		this.id = value;
	}

	public void validar() {
		

		// Recorre los campos que tengan la anotacion @ValidarRequerido
		// Y se fija que no esten vacios
		try {
			for (Field field : this.getClass().getDeclaredFields()) {
				field.setAccessible(true); //Necesario para leer los campos privados con reflection
				Object value = field.get(this);
				
				if (field.isAnnotationPresent(ValidarRequerido.class)) {
					ValidarRequerido anotacion = field.getAnnotation(ValidarRequerido.class);
	

					if (value == null) {
						throw new Error("Campo null " + field.getName() + ":" + anotacion.mensaje());
					}
					
					if ((value instanceof String) && ((String)value).trim().length()==0) {
						throw new Error("Campo vacio " + field.getName() + ":" + anotacion.mensaje());
					}
				}
				
				if (field.isAnnotationPresent(ValidarRango.class)) {
					ValidarRango rango= field.getAnnotation(ValidarRango.class);
					
					if (value instanceof Integer) {
						if (((Integer)value)<rango.min()) {
							throw new Error(rango.mensaje());
						}
						
						if (((Integer)value)>rango.max()) {
							throw new Error(rango.mensaje());
						}

					}
					
					if (value instanceof Double) {
						if (((Double)value)<rango.min()) {
							throw new Error(rango.mensaje());
						}
						
						if (((Double)value)>rango.max()) {
							throw new Error(rango.mensaje());
						}
					}
					
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// un sneaky throws de lombok casero
			throw new Error(e.getMessage());
		}

	}

}
