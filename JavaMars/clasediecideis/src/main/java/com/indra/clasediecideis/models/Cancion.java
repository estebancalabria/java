package com.indra.clasediecideis.models;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter @Setter
@Table(name="Song")
public class Cancion{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="Title")
	private String titulo;
	
	@Column(name="Artist")
	private String autor;
	
	@Column(name="Record")
	private String disco;
	
	@Column(name="Duration")
	private int segundos;
}
