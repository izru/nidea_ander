package com.ipartek.formacion.nidea.pojo;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Bebida {
	private int id;
	@NotNull(message = "No puede estar vacío")
	@Size(min = 3, max = 45)
	private String nombre;

	@DecimalMin("0.1")
	private float precio;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bebida() {
		super();
		this.id = -1;
		this.nombre = "";
		this.precio = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre != null) {
			this.nombre = nombre;
		} else {
			this.nombre = "";
		}
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {

		if (precio > 0) {
			this.precio = precio;
		} else {
			this.precio = (float) 0.1;
		}

	}

	@Override
	public String toString() {
		return "Bebida [id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
	}

}
