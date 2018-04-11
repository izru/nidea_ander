package com.ipartek.formacion.nidea.ejemplos;

public class Circulo extends ObjetoGrafico {
	int diametro;

	public Circulo() {
		super();
		this.diametro = 0;
	}

	public Circulo(int diametro) {
		this();
		this.diametro = diametro;
	}

	public int getDiametro() {
		return diametro;
	}

	public void setDiametro(int diametro) {
		this.diametro = diametro;
	}

	@Override
	void dibujar() {
		System.out.println("Pintar Circulo");

	}

	@Override
	public void imprimir() {
		// TODO Auto-generated method stub

	}

}
