package org.indra.claseTrece.models;

import org.indra.claseTrece.models.annotations.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="Accion")
public class Accion extends ObjetoDeNegocio<String>{

	@Setter
	@Getter
	@ValidarRequerido(mensaje = "La accion debe tener simbolo")
	//@ValidarRequerido("La accion debe tener simbolo") con value
	@Column(name="simbolo")
	@Id
	private String simbolo;

	@Setter
	@Getter
	@ValidarRequerido(mensaje = "El nombre de la accion no puede ser vacio")
	@Column(name="nombre")
	private String nombre;

	@Setter
	@Getter
	@ValidarRango(mensaje = "La ultima cotizacion no puede ser menor a 0", min = 0)
	@Column(name="cotizacion")
	private double ultimaCotizacion;

	//Lo comentamos porque lo subimos al objeto de negocio
	/*public void validar() {
		if ((this.simbolo == null) || this.simbolo.trim().length() == 0) {
			throw new Error("La accion debe tener simbolo");
		}

		if ((this.nombre == null) || this.nombre.trim().length() == 0) {
			throw new Error("El nombre de la accion no puede ser vacio");
		}

		if (ultimaCotizacion < 0) {
			throw new Error("La cotizacion de la accion debe ser un numero positivo");
		}

	}*/
	
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.getSimbolo();
	}
	
	@Override
	public void setId(String value) {
		// TODO Auto-generated method stub
		super.setId(value);
		this.setSimbolo(value);
	}
}
