package iwsit2106.myupmclassroom.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import iwsit2106.myupmclassroom.App;
import iwsit2106.myupmclassroom.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import servidor.ObtencionDeRol;
import utilidades.Cifrado;

public class ListaPersonal {
	private BDPersonalUPM bdPersonalUPM;
	private List<IPersonalUPM> personal;

	@FXML
	private ComboBox<String> selectorPersonal;

	@FXML
	private ComboBox<String> selectorCategoria;

	@FXML
	private Label textoMatricula;

	@FXML
	private Label textoTrabajador;

	@FXML
	private Label textoCategoria;

	@FXML
	private Label textoCodigoPersonal;

	@FXML
	private Label textoAno;

	@FXML
	private Label textoError;

	@FXML
	private Label contadorPersonal;

	@FXML
	private TextField dni;

	@FXML
	private TextField nombre;

	@FXML
	private TextField primerApellido;

	@FXML
	private TextField segundoApellido;

	@FXML
	private TextField correo;

	@FXML
	private TextField contrasena;

	@FXML
	private TextField matricula;

	@FXML
	private TextField trabajador;

	@FXML
	private TextField codigoPersonal;

	@FXML
	private TextField ano;

	@FXML
	private Button botonEnviar;

	public ListaPersonal() {
		// Carga el personal de la base de datos
		bdPersonalUPM = new BDPersonalUPM();
		personal = bdPersonalUPM.obtener();
	}

	/**
	 * Inicializa el controlador del personal
	 */
	@FXML
	private void initialize() {
		// Hace invisible la zona de datos específicos para que se activen según lo
		// elegido
		textoMatricula.setManaged(false);
		textoTrabajador.setManaged(false);
		textoCategoria.setManaged(false);
		textoCodigoPersonal.setManaged(false);
		textoAno.setManaged(false);
		matricula.setManaged(false);
		trabajador.setManaged(false);
		selectorCategoria.setManaged(false);
		codigoPersonal.setManaged(false);
		ano.setManaged(false);

		// Carga el personal de la base de datos
		bdPersonalUPM = new BDPersonalUPM();
		personal = bdPersonalUPM.obtener();

		// Añade los tipos de personal al selector
		ObservableList<String> listaPersonal = FXCollections.observableArrayList();
		selectorPersonal.setItems(listaPersonal);
		listaPersonal.add("Alumno");
		listaPersonal.add("PDI");
		listaPersonal.add("PAS");

		// Añade los tipos de PAS al selector
		ObservableList<String> listaCategorias = FXCollections.observableArrayList();
		selectorCategoria.setItems(listaCategorias);
		listaCategorias.add(TCategoria.AYUDANTE.toString());
		listaCategorias.add(TCategoria.CATEDRATICO.toString());
		listaCategorias.add(TCategoria.PROFESOR_AYUDANTE_DOCTOR.toString());
		listaCategorias.add(TCategoria.PROFESOR_CONTRATADO_DOCTOR.toString());
		listaCategorias.add(TCategoria.TITULAR.toString());
		selectorCategoria.setValue(TCategoria.AYUDANTE.toString());

		// Actualiza el contador de aulas
		contadorPersonal.setText(String.format("Número de Personas: %d", personal.size()));
	}

	public BDPersonalUPM getBdPersonalUPM() {
		return bdPersonalUPM;
	}

	public void setBdPersonalUPM(BDPersonalUPM bdPersonalUPM) {
		this.bdPersonalUPM = bdPersonalUPM;
	}

	public List<IPersonalUPM> getPersonal() {
		return personal;
	}

	public void setPersonal(List<IPersonalUPM> personal) {
		this.personal = personal;
	}

	public void anadirPersonal(IPersonalUPM persona) {
		personal.add(persona);
		bdPersonalUPM.guardar(personal);
	}

	public IPersonalUPM obtenerPersonal(String id) {
		IPersonalUPM aux = null;

		for (IPersonalUPM persona : personal) {
			if (persona.getDni().equals(id))
				aux = persona;
		}

		return aux;
	}

	public void borrarPersonal(String id) {
		Iterator<IPersonalUPM> it = personal.iterator();

		while (it.hasNext()) {
			IPersonalUPM aux = it.next();
			if (aux.getDni() == id)
				it.remove();
		}

		bdPersonalUPM.guardar(personal);
	}

	public void borrarPersonalCascada() {
		personal.clear();
		bdPersonalUPM.guardar(personal);
	}

	/**
	 * Redirige a la vista principal
	 */
	@FXML
	private void volverAtras() {
		App.cambiarVista("inicio");
	}

	/**
	 * Muestra/Oculta los Labels y TextFields dependiendo del tipo de personal
	 * seleccionado
	 */
	@FXML
	private void cambioSelectorPersonal() {
		switch (selectorPersonal.getValue()) {
			case "Alumno":
				textoMatricula.setVisible(true);
				textoMatricula.setManaged(true);
				textoTrabajador.setVisible(false);
				textoTrabajador.setManaged(false);
				textoCategoria.setVisible(false);
				textoCategoria.setManaged(false);
				textoCodigoPersonal.setVisible(false);
				textoCodigoPersonal.setManaged(false);
				textoAno.setVisible(false);
				textoAno.setManaged(false);
				//
				matricula.setVisible(true);
				matricula.setManaged(true);
				trabajador.setVisible(false);
				trabajador.setManaged(false);
				selectorCategoria.setVisible(false);
				selectorCategoria.setManaged(false);
				codigoPersonal.setVisible(false);
				codigoPersonal.setManaged(false);
				ano.setVisible(false);
				ano.setManaged(false);
				break;
			case "PDI":
				textoMatricula.setVisible(false);
				textoMatricula.setManaged(false);
				textoTrabajador.setVisible(true);
				textoTrabajador.setManaged(true);
				textoCategoria.setVisible(true);
				textoCategoria.setManaged(true);
				textoCodigoPersonal.setVisible(false);
				textoCodigoPersonal.setManaged(false);
				textoAno.setVisible(false);
				textoAno.setManaged(false);
				//
				matricula.setVisible(false);
				matricula.setManaged(false);
				trabajador.setVisible(true);
				trabajador.setManaged(true);
				selectorCategoria.setVisible(true);
				selectorCategoria.setManaged(true);
				codigoPersonal.setVisible(false);
				codigoPersonal.setManaged(false);
				ano.setVisible(false);
				ano.setManaged(false);
				break;
			case "PAS":
				textoMatricula.setVisible(false);
				textoMatricula.setManaged(false);
				textoTrabajador.setVisible(false);
				textoTrabajador.setManaged(false);
				textoCategoria.setVisible(false);
				textoCategoria.setManaged(false);
				textoCodigoPersonal.setVisible(true);
				textoCodigoPersonal.setManaged(true);
				textoAno.setVisible(true);
				textoAno.setManaged(true);
				//
				matricula.setVisible(false);
				matricula.setManaged(false);
				trabajador.setVisible(false);
				trabajador.setManaged(false);
				selectorCategoria.setVisible(false);
				selectorCategoria.setManaged(false);
				codigoPersonal.setVisible(true);
				codigoPersonal.setManaged(true);
				ano.setVisible(true);
				ano.setManaged(true);
				break;
		}

		// Activa el botón de enviar
		botonEnviar.setDisable(false);
	}

	/**
	 * Comprueba si hay campos vacios en el formulario
	 * 
	 * @param nombre
	 * @param primerApellido
	 * @param segundoApellido
	 * @param contrasena
	 * @return boolean
	 */
	public boolean hayCamposVacios(String nombre, String primerApellido, String segundoApellido, String contrasena) {
		return nombre.isBlank() || primerApellido.isBlank() || segundoApellido.isBlank()
				|| contrasena.isBlank();
	}

	/**
	 * Comprueba que solo haya letras en el texto
	 * 
	 * @param texto
	 * @return boolean
	 */
	public boolean haySoloLetras(String texto) {
		Pattern regexp = Pattern.compile("[a-zA-Z]+");
		return regexp.matcher(texto).matches();
	}

	/**
	 * Comprueba que solo haya números en el texto
	 * 
	 * @param texto
	 * @return boolean
	 */
	public boolean haySoloNumeros(String texto) {
		Pattern regexp = Pattern.compile("[0-9]+");
		return regexp.matcher(texto).matches();
	}

	/**
	 * Comprueba que el DNI sea válido
	 * 
	 * @param dni
	 * @return boolean
	 */
	public boolean esValidoDni(String dni) {
		Pattern regexp = Pattern.compile("[0-9]{8}[A-Z]");
		String digitoControl = "TRWAGMYFPDXBNJZSQVHLCKE";
		return regexp.matcher(dni).matches()
				&& dni.charAt(8) == digitoControl.charAt(Integer.parseInt(dni.substring(0, 8)) % 23);
	}

	/**
	 * Comprueba que el DNI no exista en la Base de Datos
	 * 
	 * @param dni
	 * @return boolean
	 */
	public boolean esRepetidoDni(String dni) {
		boolean esRepetido = false;

		for (int i = 0; i < personal.size() && !esRepetido; i++) {
			if (personal.get(i).getDni().equals(dni))
				esRepetido = true;
		}

		return esRepetido;
	}

	/**
	 * Comprueba que el correo sea válido
	 * 
	 * @param correo
	 * @return
	 */
	public boolean esValidoCorreo(String correo) {
		correo = correo.toLowerCase().trim();
		return correo.endsWith("@alumnos.upm.es") || correo.endsWith("pas@upm.es") || correo.endsWith("pdi@upm.es");
	}

	/**
	 * Comprueba que el correo no exista en la Base de Datos
	 * 
	 * @param correo
	 * @return
	 */
	public boolean esRepetidoCorreo(String correo) {
		boolean esRepetido = false;

		for (int i = 0; i < personal.size() && !esRepetido; i++) {
			if (personal.get(i).getCorreo().equals(correo))
				esRepetido = true;
		}

		return esRepetido;
	}

	/**
	 * Comprueba que el tipo de personal sea válido
	 * 
	 * @param tipoPersonal
	 * @param correo
	 * @return
	 */
	public boolean esValidoTipoPersonal(String tipoPersonal, String correo) {
		String rol = ObtencionDeRol.get_UPM_AccountRol(correo).toString();
		return rol.equals(tipoPersonal.toUpperCase());
	}

	/**
	 * Comprueba que la matrícula no exista en la Base de Datos
	 * 
	 * @param matricula
	 * @return
	 */
	public boolean esRepetidoMatricula(int matricula) {
		boolean esRepetido = false;

		for (int i = 0; i < personal.size() && !esRepetido; i++) {
			if (personal.get(i).getClass() == Alumno.class) {
				Alumno alumno = (Alumno) personal.get(i);
				if (alumno.getNumeroMatricula() == matricula)
					esRepetido = true;
			}
		}

		return esRepetido;
	}

	/**
	 * Comprueba que el código de trabajador no exista en la Base de Datos
	 * 
	 * @param codigoTrabajador
	 * @return
	 */
	public boolean esRepetidoTrabajador(int codigoTrabajador) {
		boolean esRepetido = false;

		for (int i = 0; i < personal.size() && !esRepetido; i++) {
			if (personal.get(i).getClass() == PDI.class) {
				PDI pdi = (PDI) personal.get(i);
				if (pdi.getCodigoTrabajador() == codigoTrabajador)
					esRepetido = true;
			}
		}

		return esRepetido;
	}

	/**
	 * Comprueba que el código de personal no exista en la Base de Datos
	 * 
	 * @param codigoPersonal
	 * @return
	 */
	public boolean esRepetidoPersonal(int codigoPersonal) {
		boolean esRepetido = false;

		for (int i = 0; i < personal.size() && !esRepetido; i++) {
			if (personal.get(i).getClass() == PAS.class) {
				PAS pas = (PAS) personal.get(i);
				if (pas.getCodigoPersonal() == codigoPersonal)
					esRepetido = true;
			}
		}

		return esRepetido;
	}

	/**
	 * Realiza el alta del personal
	 */
	@FXML
	public void altaPersonal() {
		// Elimina el anterior error
		textoError.setText("");

		// Valida los datos generales
		if (hayCamposVacios(nombre.getText(), primerApellido.getText(), segundoApellido.getText(),
				contrasena.getText()))
			textoError.setText("Error: Quedan campos por rellenar!");
		else if (!haySoloLetras(nombre.getText()) || !haySoloLetras(primerApellido.getText())
				|| !haySoloLetras(segundoApellido.getText())) {
			textoError.setText("Error: Hay algún campo que contiene números y no puede tenerlos!");
		} else if (!esValidoDni(dni.getText()))
			textoError.setText("Error: Este DNI no es válido!");
		else if (esRepetidoDni(dni.getText()))
			textoError.setText("Error: Este DNI ya está en la base de datos!");
		else if (!esValidoCorreo(correo.getText()))
			textoError.setText("Error: Este correo no es válido!");
		else if (esRepetidoCorreo(correo.getText()))
			textoError.setText("Error: Este correo ya está en la base de datos!");
		else if (!esValidoTipoPersonal(selectorPersonal.getValue(), correo.getText()))
			textoError.setText("Error: El tipo de personal seleccionado es distinto al correo introducido!");
		else {
			// Se crea una lista con los parametros comunes
			ArrayList<String> parametros = new ArrayList<String>();
			parametros.add(dni.getText());
			parametros.add(nombre.getText());
			parametros.add(primerApellido.getText());
			parametros.add(segundoApellido.getText());
			parametros.add(correo.getText());
			parametros.add(Cifrado.cifrar(contrasena.getText()));

			// Se crea el usuario dependiendo del tipo seleccionado, validando los datos
			try {
				switch (selectorPersonal.getValue()) {
					case "Alumno":
						if (!haySoloNumeros(matricula.getText()))
							textoError.setText("Error: Esta matrícula no es válida!");
						else if (esRepetidoMatricula(Integer.parseInt(matricula.getText())))
							textoError.setText("Error: Esta matrícula ya está en la base de datos!");
						else {
							parametros.add(matricula.getText());
							anadirPersonal(FactoriaPersonal.creaPersonal("Alumno", parametros));
							volverAtras();
						}
						break;
					case "PDI":
						if (!haySoloNumeros(trabajador.getText()))
							textoError.setText("Error: Este código de trabajador no es válido!");
						else if (esRepetidoTrabajador(Integer.parseInt(trabajador.getText())))
							textoError.setText("Error: Este código de trabajador ya está en la base de datos!");
						else {
							parametros.add(trabajador.getText());
							parametros.add(selectorCategoria.getValue());
							anadirPersonal(FactoriaPersonal.creaPersonal("PDI", parametros));
							volverAtras();
						}
						break;
					case "PAS":
						if (!haySoloNumeros(codigoPersonal.getText()))
							textoError.setText("Error: Este código de personal no es válido!");
						else if (esRepetidoPersonal(Integer.parseInt(codigoPersonal.getText())))
							textoError.setText("Error: Este código de personal ya está en la base de datos!");
						else if (!haySoloNumeros(ano.getText()) || Integer.parseInt(ano.getText()) < 1900
								|| Integer.parseInt(ano.getText()) > 2022)
							textoError.setText("Error: Este año de incorporación no es válido!");
						else {
							parametros.add(codigoPersonal.getText());
							parametros.add(ano.getText());
							anadirPersonal(FactoriaPersonal.creaPersonal("PAS", parametros));
							volverAtras();
						}
						break;
				}
			} catch (Exception e) {
				System.out.println("Se han introducido más parametros de los necesarios");
			}
		}
	}
}
