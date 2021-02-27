package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Clase Pelicula con su titulo, edad recomendada, fecha de estreno y el nombre del director
 * @author Raul
 *
 */
@Entity
public class Pelicula {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	/**Titulo de la pelicula*/
	private String titulo;
	
	/**Edad recomendada de la pelicula*/
	@Column
	private int edadMinima;
	
	
	/**Fecha de estreno de la pelicula*/
	@Column
	private String fechaEstreno;
	
	/**Nombre del director de la pelicula*/
	@ManyToOne
	@JoinColumn(name="director")
	private Director director;
	
	
	/**
	 * Construcctor de pelicula sin datos*/
	public Pelicula() {
		this.titulo = "";
		this.edadMinima = 0;
		this.fechaEstreno = "";
		this.director = null;
	}
	
	/**Constructor de la pelicula con todos sus datos*/
	public Pelicula(String titulo, int edadMinima, String fechaEstreno, Director director) {
		this.titulo = titulo;
		this.edadMinima = edadMinima;
		this.fechaEstreno = fechaEstreno;
		this.director = director;

	}
	
	/**Contructor de Pelicula sin su Director*/
	public Pelicula(String titulo, int edadMinima, String fechaEstreno) {
		this.titulo = titulo;
		this.edadMinima = edadMinima;
		this.fechaEstreno = fechaEstreno;
	}

	/**
	 * @return
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @return
	 */
	public int getEdadMinima() {
		return edadMinima;
	}
	/**
	 * @param edadMinima
	 */
	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}
	/**
	 * @return
	 */
	public String getFechaEstreno() {
		return fechaEstreno;
	}
	/**
	 * @param fechaEstreno
	 */
	public void setFechaEstreno(String fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}
	/**
	 * @return
	 */
	public Director getDirector() {
		return director;
	}
	/**
	 * @param director
	 */
	public void setDirector(Director director) {
		this.director = director;
	}
	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
}
