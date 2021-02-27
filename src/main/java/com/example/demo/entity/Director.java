package com.example.demo.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**Clase Director con su nombre */
@Entity
public class Director {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String nombre;
	
	/**Constructor vacio de Director*/
	public Director() {
		this.nombre="";
	}
	
	/**Contructor de administrador con su nombre*/
	public Director(String nombre) {
		this.nombre=nombre;
	}
	
	/**Getter del nombre*/
	public String getNombre() {
		return nombre;
	}
	
	/**Setter del nombre*/
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * @param nombre
	 * @return
	 */
	public static boolean validarNombre(String nombre) {
		boolean check=false;
		String patron="[0-9]";
		Pattern p = Pattern.compile(patron);
		Matcher m = p.matcher(nombre);
		check=m.matches();
		return !check;
	}

	@Override
	public String toString() {
		return "Director [id=" + id + ", nombre=" + nombre + "]";
	}
}
