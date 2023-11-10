package org.itnow.cursoSpring.clasecuatro.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor()
@NoArgsConstructor()
public abstract class ObjetoModels {

	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

}
