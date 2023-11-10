package org.itnow.cursoSpring.clasecuatro.models;

import jakarta.persistence.*;
import lombok.*;

//por ahora le saco la herencia para que funcione por ahora

@ToString()
@AllArgsConstructor()
@NoArgsConstructor()
@Entity
@Table(name="automovil")  
public class Automovil /*extends ObjetoModels*/ {
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Getter @Setter
	@Column(name="marca")
	private String marca; 
	
	@Getter @Setter
	@Column(name="modelo")
	private String modelo;
	
	@Getter @Setter
	@Column(name="year")
	private int año;
}
