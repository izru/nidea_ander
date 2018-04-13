package com.ipartek.formacion.nidea.pojo;

/**
 * Clase de usuario publico
 * 
 * @author Curso
 *
 */
public class Usuario {

	private int id;
	private String nombre;

	public Usuario() {
		super();
		this.id = -1;
		this.nombre = "";
	}

	public Usuario(int id2, String nombre2) {
		this();
		this.id = id2;
		this.nombre = nombre2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + "]";
	}

}
