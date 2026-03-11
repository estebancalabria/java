package org.indra.claseDoce.models;

import java.time.LocalDate;
import lombok.*;

@Builder
public class Usuario {
	@Setter @Getter
	private String nickName;
	@Setter @Getter
	private String password;
	@Setter @Getter
	private LocalDate fechaDeNacimiento;
	@Setter @Getter
	private String correo;
}
