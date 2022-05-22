package iwsit2106.myupmclassroom.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Aula implements Serializable {
	private int id;
	private String nombreCentro;
	private double superficie;
	private int aforo;
	private TAula tipo;
	private List<Elemento> elementos;
	private PAS pas;
	private List<IObservador> observadores;

	public Aula(int id, String nombreCentro, double superficie, int aforo, TAula tipo, ArrayList<Elemento> elementos,
			PAS pas) {
		this.id = id;
		this.nombreCentro = nombreCentro;
		this.superficie = superficie;
		this.aforo = aforo;
		this.tipo = tipo;
		this.elementos = elementos;
		this.pas = pas;
		this.observadores = new ArrayList<IObservador>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreCentro() {
		return nombreCentro;
	}

	public void setNombreCentro(String nombreCentro) {
		this.nombreCentro = nombreCentro;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public TAula getTipo() {
		return tipo;
	}

	public void setTipo(TAula tipo) {
		this.tipo = tipo;
	}

	public void anadirElemento(Elemento elemento) {
		elementos.add(elemento);
	}

	public Elemento obtenerElemento(int id) {
		Elemento aux = null;

		for (Elemento elemento : elementos) {
			if (elemento.getNumeroBastidor() == id)
				aux = elemento;
		}

		return aux;
	}

	public void borrarElemento(int id) {
		Iterator<Elemento> it = elementos.iterator();

		while (it.hasNext()) {
			Elemento aux = it.next();
			if (aux.getNumeroBastidor() == id)
				it.remove();
		}
	}

	public void borrarElementosCascada() {
		elementos.clear();
	}

	public void anadirPAS(PAS pas) {
		this.pas = pas;
	}

	public PAS obtenerPAS() {
		return this.pas;
	}

	public void borrarPAS() {
		this.pas = null;
	}

	public void anadirObservador(IObservador observador) {
		observadores.add(observador);
	}

	public IObservador obtenerObservador(String id) {
		IObservador aux = null;

		for (IObservador observador : observadores) {
			if (observador.getDni().equals(id))
				aux = observador;
		}

		return aux;
	}

	public void borrarObservador(String id) {
		Iterator<IObservador> it = observadores.iterator();

		while (it.hasNext()) {
			IObservador aux = it.next();
			if (aux.getDni() == id)
				it.remove();
		}
	}
}
