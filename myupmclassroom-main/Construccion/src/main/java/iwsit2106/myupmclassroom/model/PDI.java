package iwsit2106.myupmclassroom.model;

public class PDI extends Observador {
	private int codigoTrabajador;
	private TCategoria categoria;

	public PDI(String dni, String nombre, String primerApellido, String segundoApellido, String correo,
			String contrasena, int codigoTrabajador, TCategoria categoria) {
		super(dni, nombre, primerApellido, segundoApellido, correo, contrasena);
		this.codigoTrabajador = codigoTrabajador;
		this.categoria = categoria;
	}

	public int getCodigoTrabajador() {
		return codigoTrabajador;
	}

	public void setCodigoTrabajador(int codigoTrabajador) {
		this.codigoTrabajador = codigoTrabajador;
	}

	public TCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(TCategoria categoria) {
		this.categoria = categoria;
	}
}
