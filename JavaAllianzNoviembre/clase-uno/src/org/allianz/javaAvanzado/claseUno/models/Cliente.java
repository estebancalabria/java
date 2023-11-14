package org.allianz.javaAvanzado.claseUno.models;

import lombok.*;

@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends ObjetoDeNegocio {
	
	@Getter @Setter
	private int documento; 
	@Getter @Setter
	private String nombre;
	@Getter @Setter
	private String apellido;
	
    /*public int getDocumento() {
		return documento;
	}
    
	public void setDocumento(int documento) {
		this.documento = documento;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		return String.format("[%d] %s %s", 
				this.documento,
				this.apellido.toUpperCase(),
				this.nombre);
	}*/
}
