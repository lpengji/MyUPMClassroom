package iwsit2106.myupmclassroom.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Observador extends PersonalUPM implements IObservador {
	private List<Aula> aulas;

	public Observador(String dni, String nombre, String primerApellido, String segundoApellido, String correo,
			String contrasena) {
		super(dni, nombre, primerApellido, segundoApellido, correo, contrasena);
		this.aulas = new ArrayList<Aula>();
	}

	public void anadirAula(Aula aula) {
		aulas.add(aula);
	}

	public Aula obtenerAula(int id) {
		Aula aux = null;

		for (Aula aula : aulas) {
			if (aula.getId() == id)
				aux = aula;
		}

		return aux;
	}

	public void borrarAula(int id) {
		Iterator<Aula> it = aulas.iterator();

		while (it.hasNext()) {
			Aula aux = it.next();
			if (aux.getId() == id)
				it.remove();
		}
	}
}
