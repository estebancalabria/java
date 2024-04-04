package org.tiben;

import java.lang.reflect.Field;

public class ObjetoDeNegocio {

	private boolean isEmptyString(Object value) {
		return (value instanceof String && ((String) value).trim().length() == 0);
	}

	public void validar() {
		for (Field field : this.getClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(Requerido.class)) {
				field.setAccessible(true);
				try {
					Object value = field.get(this);
					if ((value == null) || isEmptyString(value))) {
						Requerido annotation = field.getAnnotation(Requerido.class);
						throw new Error(annotation.mensaje() + ": " + field.getName());
					}
				} catch (IllegalArgumentException | IllegalAccessException | Error e) {
					throw new Error(e.getMessage());
				}
			}
		}
	}
}
