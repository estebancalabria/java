package org.gobvasco.cursomsa.clasetres.jpademo.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Song")
public class Cancion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title", nullable=false)
	private String titulo;
	
	@OneToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="artista_id", nullable = true)  //Vamos a hacerlo nuleable a proposito
	private Artista artista;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
}
