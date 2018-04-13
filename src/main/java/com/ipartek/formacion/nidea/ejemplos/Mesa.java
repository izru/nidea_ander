package com.ipartek.formacion.nidea.ejemplos;

public class Mesa implements Ordenable {

	int numPatas;

	public Mesa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mesa(int numPatas) {
		this();
		this.numPatas = numPatas;
	}

	public int getNumPatas() {
		return numPatas;
	}

	public void setNumPatas(int numPatas) {
		this.numPatas = numPatas;
	}

	@Override
	public int getValor() {

		return this.numPatas;
	}
}
