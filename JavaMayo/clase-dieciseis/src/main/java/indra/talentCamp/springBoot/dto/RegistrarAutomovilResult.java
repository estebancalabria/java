package indra.talentCamp.springBoot.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class RegistrarAutomovilResult {
	private boolean operacionExitosa;
	private String mensaje;
}
