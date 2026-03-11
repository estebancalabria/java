package org.indra.claseSiete;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString
public class Cliente {
	@Getter @Setter @NonNull
	private String nombre;
	@Getter @Setter
	private int edad;
	
	/*public Cliente(String nombre, int edad) {
		super();
		this.nombre = nombre;
		this.edad = edad;
	}
	
	public Cliente() {

	}*/
	
	/*String getNombre() {
		return nombre;
	}
	void setNombre(String nombre) {
		this.nombre = nombre;
	}
	int getEdad() {
		return edad;
	}
	void setEdad(int edad) {
		this.edad = edad;
	}*/
}
