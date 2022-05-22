package iwsit2106.myupmclassroom.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import iwsit2106.myupmclassroom.model.FactoriaPersonal;

public class FactoriaPersonalTest {

    private List<String> parametros;

    @Before
    public void setUp() {
        parametros = new ArrayList<String>();
    }

    /**
     * Número de parámetros válidos para crear un Alumno
     * 
     * @throws Exception
     */
    @Test
    public void testAlumnoCorrecto() throws Exception {
        for (int i = 0; i < 7; i++)
            parametros.add("1");

        assertNotNull(FactoriaPersonal.creaPersonal("Alumno", parametros));
    }

    /**
     * Número de parámetros insuficientes para crear un Alumno
     * 
     * @throws Exception
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAlumnoInsuficiente() throws Exception {
        for (int i = 0; i < 6; i++)
            parametros.add("1");

        assertNull(FactoriaPersonal.creaPersonal("Alumno", parametros));
    }

    /**
     * Número de parámetros que exceden para crear un Alumno
     * 
     * @throws Exception
     */
    @Test(expected = Exception.class)
    public void testAlumnoExcede() throws Exception {
        for (int i = 0; i < 8; i++)
            parametros.add("1");

        assertNull(FactoriaPersonal.creaPersonal("Alumno", parametros));
    }

    /**
     * Tipo de Personal incorrecto
     * 
     * @throws Exception
     */
    @Test
    public void testTipoPersonalIncorrecto() throws Exception {
        for (int i = 0; i < 7; i++)
            parametros.add("1");

        assertNull(FactoriaPersonal.creaPersonal("Hola", parametros));
    }

    /**
     * Tipo de Personal vacío
     * 
     * @throws Exception
     */
    @Test
    public void testTipoPersonalVacio() throws Exception {
        for (int i = 0; i < 7; i++)
            parametros.add("1");

        assertNull(FactoriaPersonal.creaPersonal("", parametros));
    }
}