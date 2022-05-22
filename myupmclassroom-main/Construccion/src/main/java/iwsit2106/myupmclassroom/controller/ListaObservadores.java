package iwsit2106.myupmclassroom.controller;

import java.util.Iterator;
import java.util.List;

import iwsit2106.myupmclassroom.App;
import iwsit2106.myupmclassroom.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class ListaObservadores {
	private BDObservador bdObservador;
	private List<IObservador> observadores;
	private ListaPersonal controladorPersonal;
	private ListaAulas controladorAulas;

	@FXML
	private ComboBox<String> selectorObservador;

	@FXML
	private ComboBox<String> selectorAula;

	@FXML
	private Label textoError;

	@FXML
	private Label contadorObservadores;

	/**
	 * Inicializa el controlador de los observadores
	 */
	@FXML
	private void initialize() {
		// Carga los observadores de la base de datos
		bdObservador = new BDObservador();
		observadores = bdObservador.obtener();

		// Instancia el controlador de personal para poder obtener los Alumnos/PDI
		controladorPersonal = new ListaPersonal();

		// Instancia el controlador de aulas para obtenerlas
		controladorAulas = new ListaAulas();

		// Añade los alumnos y PDI al selector
		ObservableList<String> listaObservadores = FXCollections.observableArrayList();
		selectorObservador.setItems(listaObservadores);
		for (IPersonalUPM personalUPM : controladorPersonal.getPersonal()) {
			if (personalUPM.getClass() == Alumno.class || personalUPM.getClass() == PDI.class)
				listaObservadores.add(personalUPM.getDni());
		}

		// Añade las aulas al selector
		ObservableList<String> listaAulas = FXCollections.observableArrayList();
		selectorAula.setItems(listaAulas);
		for (Aula aula : controladorAulas.getAulas())
			listaAulas.add(Integer.toString(aula.getId()));

		// Actualiza el contador de observadores
		contadorObservadores.setText(String.format("Número de Observadores: %d", observadores.size()));
	}

	public void anadirObservador(IObservador observador, Aula aula) {
		observador.anadirAula(aula);
		aula.anadirObservador(observador);
		observadores.add(observador);
		bdObservador.guardar(observadores);
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

		bdObservador.guardar(observadores);
	}

	public void borrarObservadoresCascada() {
		observadores.clear();
		bdObservador.guardar(observadores);
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
		return selectorObservador.getValue() == null || selectorAula.getValue() == null;
	}

	/**
	 * Comprueba que el personal seleccionado no esté suscrito al aula
	 * 
	 * @param dni
	 * @param id
	 * @return
	 */
	public boolean estaSuscrito(String dni, int id) {
		IObservador observador = obtenerObservador(dni);
		return observador != null && observador.obtenerAula(id) != null;
	}

	/**
	 * Realiza la suscripcion al aula
	 */
	@FXML
	public void suscribirseObservador() {
		// Elimina el anterior error
		textoError.setText("");

		if (hayCamposVacios())
			textoError.setText("Error: Quedan campos por rellenar!");
		else if (estaSuscrito(selectorObservador.getValue(), Integer.parseInt(selectorAula.getValue())))
			textoError.setText("Error: El Alumno/PDI seleccionado ya está observando el aula!");
		else {
			// Busca al observador seleccionado por el usuario
			List<IPersonalUPM> listadoPersonal = controladorPersonal.getPersonal();
			IObservador observador = null;

			for (int i = 0; i < listadoPersonal.size(); i++) {
				if (listadoPersonal.get(i).getDni().equals(selectorObservador.getValue())) {
					if (listadoPersonal.get(i).getClass() == Alumno.class)
						observador = (Alumno) listadoPersonal.get(i);
					else if (listadoPersonal.get(i).getClass() == PDI.class)
						observador = (PDI) listadoPersonal.get(i);
				}
			}

			// Busca el aula seleccionada por el usuario
			List<Aula> listadoAulas = controladorAulas.getAulas();
			Aula aula = null;

			for (int i = 0; i < listadoAulas.size(); i++) {
				if (listadoAulas.get(i).getId() == Integer.parseInt(selectorAula.getValue()))
					aula = listadoAulas.get(i);
			}

			anadirObservador(observador, aula);
			volverAtras();
		}
	}
}
