package iwsit2106.myupmclassroom.model;

public class Alumno extends Observador {
	private int numeroMatricula;

	public Alumno(String dni, String nombre, String primerApellido, String segundoApellido, String correo,
			String contrasena, int numeroMatricula) {
		super(dni, nombre, primerApellido, segundoApellido, correo, contrasena);
		this.numeroMatricula = numeroMatricula;
	}

	public int getNumeroMatricula() {
		return numeroMatricula;
	}

	public void setNumeroMatricula(int numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}
}
