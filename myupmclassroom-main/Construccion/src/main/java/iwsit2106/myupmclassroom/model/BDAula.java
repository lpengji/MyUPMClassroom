package iwsit2106.myupmclassroom.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BDAula implements IBDAula {
    /**
     * Devuelve las aulas de la base de datos
     */
    public List<Aula> obtener() {
        ArrayList<Aula> aulas = new ArrayList<Aula>();

        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("aulas.dat"));
            Object obj = inputStream.readObject();
            aulas = (ArrayList<Aula>) obj;
            inputStream.close();
        } catch (Exception e) {
            System.out.println("El fichero está vacío");
        }

        return aulas;
    }

    /**
     * Guarda todas las aulas en la base de datos
     */
    public void guardar(List<Aula> aulas) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("aulas.dat"));
            output.writeObject(aulas);
            output.close();
        } catch (Exception e) {
            System.out.println("El fichero está vacío");
        }
    }
}
