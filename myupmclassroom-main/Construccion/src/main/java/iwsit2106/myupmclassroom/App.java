package iwsit2106.myupmclassroom;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private static Scene escena;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenario) throws IOException {
        escena = new Scene(cargarFXML("inicio"), 1040, 620);
        escenario.setScene(escena);
        escenario.setResizable(false);
        escenario.setTitle("MyUPMClasroom");
        escenario.show();
    }

    /**
     * Cambia la vista del programa
     * 
     * @param fxml
     */
    public static void cambiarVista(String fxml) {
        try {
            escena.setRoot(cargarFXML(fxml));
        } catch (Exception e) {
            System.out.println("FXML no encontrado");
        }
    }

    /**
     * Carga el FXML para la vista
     * 
     * @param fxml
     * @return Parent
     * @throws IOException
     */
    private static Parent cargarFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
