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
	private String password;
	private Rol rol;

	public Usuario() {
		super();
		this.id = -1;
		this.nombre = "";
		this.rol = new Rol();
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
		return "Usuario [id=" + id + ", nombre=" + nombre + ", password =" + password + "]";
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
