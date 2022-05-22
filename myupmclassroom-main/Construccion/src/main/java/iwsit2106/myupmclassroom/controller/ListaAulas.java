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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ListaAulas {
	private BDAula bdAula;
	private List<Aula> aulas;
	private ListaPersonal controladorPersonal;

	@FXML
	private ComboBox<String> selectorPAS;

	@FXML
	private ComboBox<String> selectorTipoAula;

	@FXML
	private Label textoError;

	@FXML
	private Label contadorAulas;

	@FXML
	private TextField id;

	@FXML
	private TextField nombreCentro;

	@FXML
	private TextField superficie;

	@FXML
	private TextField aforo;

	@FXML
	private TextField bastidorTecho;

	@FXML
	private TextField bastidorParedFrontal;

	@FXML
	private TextField bastidorParedTrasera;

	public ListaAulas() {
		bdAula = new BDAula();
		aulas = bdAula.obtener();
	}

	/**
	 * Inicializa el controlador de las aulas
	 */
	@FXML
	private void initialize() {
		// Carga las aulas de la base de datos
		bdAula = new BDAula();
		aulas = bdAula.obtener();

		// Instancia el controlador de personal para poder obtener los PAS
		controladorPersonal = new ListaPersonal();

		// Añade los DNIs de PAS al selector
		ObservableList<String> listaPAS = FXCollections.observableArrayList();
		selectorPAS.setItems(listaPAS);
		for (IPersonalUPM personalUPM : controladorPersonal.getPersonal()) {
			if (personalUPM.getClass() == PAS.class)
				listaPAS.add(personalUPM.getDni());
		}

		// Añade los tipos de aulas al selector
		ObservableList<String> listaTiposAulas = FXCollections.observableArrayList();
		selectorTipoAula.setItems(listaTiposAulas);
		listaTiposAulas.add(TAula.TEORIA.toString());
		listaTiposAulas.add(TAula.LABORATORIO.toString());
		listaTiposAulas.add(TAula.MIXTA.toString());

		// Actualiza el contador de aulas
		contadorAulas.setText(String.format("Número de Aulas: %d", aulas.size()));
	}

	public List<Aula> getAulas() {
		return this.aulas;
	}

	public void anadirAula(Aula aula) {
		aulas.add(aula);
		bdAula.guardar(aulas);
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

		bdAula.guardar(aulas);
	}

	public void borrarAulasCascada() {
		aulas.clear();
		bdAula.guardar(aulas);
	}

	/**
	 * Redirige a la vista principal
	 */
	@FXML
	private void volverAtras() {
		App.cambiarVista("inicio");
	}

	/**
	 * Comprueba si hay campos vacios en el formulario
	 * 
	 * @return boolean
	 */
	public boolean hayCamposVacios() {
		return id.getText().isBlank() || nombreCentro.getText().isBlank() || superficie.getText().isBlank()
				|| aforo.getText().isBlank() || selectorPAS.getValue() == null || selectorTipoAula.getValue() == null
				|| bastidorTecho.getText().isBlank() || bastidorParedFrontal.getText().isBlank()
				|| bastidorParedTrasera.getText().isBlank();
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
	 * Comprueba si el texto es un número decimal (double)
	 * 
	 * @param numero
	 * @return boolean
	 */
	public boolean esNumeroDecimal(String numero) {
		boolean esNumeroDecimal = true;

		// Si lanza una excepción, es que no es un decimal
		try {
			Double.parseDouble(numero);
		} catch (NumberFormatException e) {
			esNumeroDecimal = false;
		}

		return esNumeroDecimal;
	}

	/**
	 * Comprueba que el ID no exista en la Base de Datos
	 * 
	 * @param id
	 * @return boolean
	 */
	public boolean esRepetidoId(int id) {
		boolean esRepetido = false;

		for (int i = 0; i < aulas.size() && !esRepetido; i++) {
			if (aulas.get(i).getId() == id)
				esRepetido = true;
		}

		return esRepetido;
	}

	/**
	 * Comprueba que los bastidores no existan en la Base de Datos
	 * 
	 * @param bastidorTecho
	 * @param bastidorParedFrontal
	 * @param bastidorParedTrasera
	 * @return boolean
	 */
	public boolean esRepetidoBastidores(int bastidorTecho, int bastidorParedFrontal, int bastidorParedTrasera) {
		boolean esRepetido = false;

		for (int i = 0; i < aulas.size() && !esRepetido; i++) {
			if (aulas.get(i).obtenerElemento(bastidorTecho) != null
					|| aulas.get(i).obtenerElemento(bastidorParedFrontal) != null
					|| aulas.get(i).obtenerElemento(bastidorParedTrasera) != null)
				esRepetido = true;
		}

		return esRepetido;
	}

	/**
	 * Realiza el alta del aula
	 */
	@FXML
	public void altaAula() {
		// Elimina el anterior error
		textoError.setText("");

		if (hayCamposVacios())
			textoError.setText("Error: Quedan campos por rellenar!");
		else if (!haySoloLetras(nombreCentro.getText()))
			textoError.setText("Error: El nombre del centro no puede tener números!");
		else if (!haySoloNumeros(id.getText()) || !haySoloNumeros(aforo.getText())
				|| !haySoloNumeros(bastidorTecho.getText()) || !haySoloNumeros(bastidorParedFrontal.getText())
				|| !haySoloNumeros(bastidorParedTrasera.getText()))
			textoError.setText("Error: Hay algún campo que contiene letras y no puede tenerlas!");
		else if (!esNumeroDecimal(superficie.getText()))
			textoError.setText("Error: La superficie no es válida!");
		else if (esRepetidoId(Integer.parseInt(id.getText())))
			textoError.setText("Error: El identificador del aula ya existe!");
		else if (esRepetidoBastidores(Integer.parseInt(bastidorTecho.getText()),
				Integer.parseInt(bastidorParedFrontal.getText()), Integer.parseInt(bastidorParedTrasera.getText())))
			textoError.setText("Error: Algún número de bastidor está repetido!");
		else {
			// Crea los elementos
			ArrayList<Elemento> elementos = new ArrayList<Elemento>();
			elementos.add(new Elemento(Integer.parseInt(bastidorTecho.getText()), TElemento.TECHO));
			elementos.add(new Elemento(Integer.parseInt(bastidorParedFrontal.getText()), TElemento.PARED_FRONTAL));
			elementos.add(new Elemento(Integer.parseInt(bastidorParedTrasera.getText()), TElemento.PARED_TRASERA));

			// Busca al PAS que ha seleccionado el usuario
			List<IPersonalUPM> listadoPersonal = controladorPersonal.getPersonal();
			PAS pas = null;

			for (int i = 0; i < listadoPersonal.size(); i++) {
				if (listadoPersonal.get(i).getClass() == PAS.class
						&& listadoPersonal.get(i).getDni().equals(selectorPAS.getValue()))
					pas = (PAS) listadoPersonal.get(i);
			}

			anadirAula(new Aula(Integer.parseInt(id.getText()), nombreCentro.getText(),
					Double.parseDouble(superficie.getText()), Integer.parseInt(aforo.getText()),
					TAula.valueOf(selectorTipoAula.getValue()), elementos, pas));

			volverAtras();
		}
	}
}
