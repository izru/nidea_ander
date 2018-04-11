package com.ipartek.formacion.nidea.ejemplos;

public abstract class ObjetoGrafico implements Imprimible {

	int x, y;

	void moverA(int x, int y) {
		this.x = x;
		this.y = y;
		System.out.println("Muevo");

	}

	abstract void dibujar();

	public ObjetoGrafico() {
		super();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
