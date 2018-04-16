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
	private String pass;
	private int idRol;
	private String nombreRol;

	public Usuario() {
		super();
		this.id = -1;
		this.nombre = "";
	}

	public Usuario(int id, String nombre) {
		this();
		this.id = id;
		this.nombre = nombre;
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
		return "Usuario [id=" + id + ", nombre=" + nombre + " idRol=" + idRol + ", nombreRol=" + nombreRol + "]";
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;

	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;

	}

	public int getIdRol() {
		return idRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass() {
		return pass;
	}

}
