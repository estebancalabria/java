package org.allianz.hellospringboot.models;

import javax.persistence.*;

import lombok.*;

@Getter @Setter
@Entity
@Table(name="Customer")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	@Column(name = "name")
	private String nombre;
	@Column(name = "last_name")
	private String apellido;
	@Column(name = "phone")
	private String telefono;
	@Column(name = "email")
	private String email;
}
