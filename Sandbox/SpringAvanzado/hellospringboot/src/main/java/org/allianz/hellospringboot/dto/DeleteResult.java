package org.allianz.hellospringboot.dto;

import lombok.*;

@Getter 
@Setter 
@AllArgsConstructor
public class DeleteResult {
	private boolean success;
	private String message;
}
