package iwsit2106.myupmclassroom.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BDObservador implements IBDObservador {
    /**
     * Devuelve los observadores de la base de datos
     */
    public List<IObservador> obtener() {
        ArrayList<IObservador> observadores = new ArrayList<IObservador>();

        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("observadores.dat"));
            Object obj = inputStream.readObject();
            observadores = (ArrayList<IObservador>) obj;
            inputStream.close();
        } catch (Exception e) {
            System.out.println("El fichero está vacío");
        }

        return observadores;
    }

    /**
     * Guarda todos los observadores en la base de datos
     */
    public void guardar(List<IObservador> observadores) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("observadores.dat"));
            output.writeObject(observadores);
            output.close();
        } catch (Exception e) {
            System.out.println("El fichero está vacío");
        }
    }
}
