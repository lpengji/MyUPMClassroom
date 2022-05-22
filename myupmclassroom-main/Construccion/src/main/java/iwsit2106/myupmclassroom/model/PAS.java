package iwsit2106.myupmclassroom.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PAS extends PersonalUPM {
	private int codigoPersonal;
	private int anoIncorporacion;
	private List<Aula> aulas;

	public PAS(String dni, String nombre, String primerApellido, String segundoApellido, String correo,
			String contrasena, int codigoPersonal, int anoIncorporacion) {
		super(dni, nombre, primerApellido, segundoApellido, correo, contrasena);
		this.codigoPersonal = codigoPersonal;
		this.anoIncorporacion = anoIncorporacion;
		this.aulas = new ArrayList<Aula>();
	}

	public int getCodigoPersonal() {
		return codigoPersonal;
	}

	public void setCodigoPersonal(int codigoPersonal) {
		this.codigoPersonal = codigoPersonal;
	}

	public int getAnoIncorporacion() {
		return anoIncorporacion;
	}

	public void setAnoIncorporacion(int anoIncorporacion) {
		this.anoIncorporacion = anoIncorporacion;
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
