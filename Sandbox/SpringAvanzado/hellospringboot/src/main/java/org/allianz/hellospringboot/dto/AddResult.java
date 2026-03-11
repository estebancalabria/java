package org.allianz.hellospringboot.dto;

import lombok.*;

@Getter 
@Setter 
@AllArgsConstructor
public class AddResult {
	private boolean success;
	private String message;
	private Object entity;
}
