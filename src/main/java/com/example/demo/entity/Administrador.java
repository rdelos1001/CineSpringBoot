package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**Clase administrador con su nombre de usuario y su contraseña */
@Entity
public class Administrador {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String usuario;
	
	@Column(name="contraseña")
	private String pwd;
	
	/**Constructor vacio de Administrador*/
	public Administrador() {
		usuario="";
		pwd="";
	}

	/**Contructor de administrador con todos sus datos*/
	public Administrador(String usuario, String pwd) {
		this.usuario = usuario;
		this.pwd = pwd;
	}
	
	/**Getter del nombre de usuario*/
	public String getUsuario() {
		return usuario;
	}
	
	/**Setter del nombre de usuario*/
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**Getter de la contraseña*/
	public String getPwd() {
		return pwd;
	}

	/**Setter de la contraseña*/
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "Administrador [ usuario=" + usuario + ", pwd=" + pwd + "]";
	}
	
}
