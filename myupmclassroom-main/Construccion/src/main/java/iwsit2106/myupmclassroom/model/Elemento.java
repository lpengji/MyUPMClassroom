package iwsit2106.myupmclassroom.model;

import java.io.Serializable;

public class Elemento implements Serializable {
	private int numeroBastidor;
	private TElemento tipo;

	public Elemento(int numeroBastidor, TElemento tipo) {
		this.numeroBastidor = numeroBastidor;
		this.tipo = tipo;
	}

	public int getNumeroBastidor() {
		return numeroBastidor;
	}

	public void setNumeroBastidor(int numeroBastidor) {
		this.numeroBastidor = numeroBastidor;
	}

	public TElemento getTipo() {
		return tipo;
	}

	public void setTipo(TElemento tipo) {
		this.tipo = tipo;
	}
}
