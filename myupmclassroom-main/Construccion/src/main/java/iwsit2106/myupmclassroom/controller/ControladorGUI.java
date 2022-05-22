package iwsit2106.myupmclassroom.controller;

import iwsit2106.myupmclassroom.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ControladorGUI {
    @FXML
    private ComboBox<String> selectorRol;

    @FXML
    private Button botonAltaPersonal;

    @FXML
    private Button botonAltaAula;

    @FXML
    private Button botonSuscribirseAula;

    /**
     * Inicializa el controlador de la interfaz principal
     */
    @FXML
    private void initialize() {
        // Añade los tipos de roles al selector
        ObservableList<String> listaRoles = FXCollections.observableArrayList();
        selectorRol.setItems(listaRoles);
        listaRoles.add("No Logueado");
        listaRoles.add("Alumno");
        listaRoles.add("PDI");
        listaRoles.add("PAS");
    }

    /**
     * Activa/Desactiva botones en función del rol seleccionado
     */
    @FXML
    private void cambioSelectorRol() {
        switch (selectorRol.getValue()) {
            case "No Logueado":
                botonAltaPersonal.setDisable(false);
                botonAltaAula.setDisable(true);
                botonSuscribirseAula.setDisable(true);
                break;
            case "Alumno":
            case "PDI":
                botonAltaPersonal.setDisable(true);
                botonAltaAula.setDisable(true);
                botonSuscribirseAula.setDisable(false);
                break;
            case "PAS":
                botonAltaPersonal.setDisable(true);
                botonAltaAula.setDisable(false);
                botonSuscribirseAula.setDisable(true);
                break;
        }
    }

    /**
     * Inicia el proceso de dar de alta a un personal de la UPM
     */
    @FXML
    private void altaPersonal() {
        App.cambiarVista("altapersonal");
    }

    /**
     * Inicia el proceso de dar de alta a un aula
     */
    @FXML
    private void altaAula() {
        App.cambiarVista("altaaula");
    }

    /**
     * Inicia el proceso de dar de suscribir a un Alumno/PDI a un aula
     */
    @FXML
    private void suscribirseAula() {
        App.cambiarVista("suscribirseaula");
    }
}
