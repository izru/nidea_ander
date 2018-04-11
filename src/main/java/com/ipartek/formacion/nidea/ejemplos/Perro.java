package com.ipartek.formacion.nidea.ejemplos;

public class Perro implements Ordenable {
	int numVacunas;

	public Perro() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Perro(int numVacunas) {
		this();
		this.numVacunas = numVacunas;
	}

	public int getNumVacunas() {
		return numVacunas;
	}

	public void setNumVacunas(int numVacunas) {
		this.numVacunas = numVacunas;
	}

}
