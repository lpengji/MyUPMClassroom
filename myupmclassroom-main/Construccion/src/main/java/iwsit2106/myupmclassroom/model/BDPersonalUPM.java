package iwsit2106.myupmclassroom.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BDPersonalUPM implements IBDPersonalUPM {
    /**
     * Devuelve el personal de la base de datos
     */
    public List<IPersonalUPM> obtener() {
        ArrayList<IPersonalUPM> personal = new ArrayList<IPersonalUPM>();

        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("personal.dat"));
            Object obj = inputStream.readObject();
            personal = (ArrayList<IPersonalUPM>) obj;
            inputStream.close();
        } catch (Exception e) {
            System.out.println("El fichero está vacío");
        }

        return personal;
    }

    /**
     * Guarda todo el personal en la base de datos
     */
    public void guardar(List<IPersonalUPM> personal) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("personal.dat"));
            output.writeObject(personal);
            output.close();
        } catch (Exception e) {
            System.out.println("El fichero está vacío");
        }
    }
}
