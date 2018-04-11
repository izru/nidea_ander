package com.ipartek.formacion.nidea.ejemplos;

public abstract class Vehiculo {
	private int puertas;
	private String color;

	private float potencia;

	public float getPotencia() {
		return potencia;
	}

	public void setPotencia(float potencia) {
		this.potencia = potencia;
	}

	public int getPuertas() {
		return puertas;
	}

	public void setPuertas(int puertas) {
		this.puertas = puertas;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Vehiculo() {
		super(); // java.lang.Object
		this.puertas = 3;
		this.color = "blanco";
		System.out.println("Instanciado vehiculo nuevo");
	}

	public abstract void arracar();

	public void encenderLuces() {
		System.out.println("Luces encendidas");
	}

	@Override
	public String toString() {

		return "Vehiculo [puertas=" + puertas + ", color =" + color + "]";
	}

}
