package iwsit2106.myupmclassroom.model;

import java.util.List;

public class FactoriaPersonal {

    /**
     * Devuelve un Alumno/PDI/PAS dependiendo de los atributos recibidos
     * 
     * @param tipo
     * @param parametros
     * @return
     * @throws Exception
     */
    public static IPersonalUPM creaPersonal(String tipo, List<String> parametros) throws Exception {
        IPersonalUPM personal = null;

        switch (tipo) {
            case "Alumno":
                // Si hay más de 7 elementos en la lista de parametros, no es un Alumno
                if (parametros.size() > 7)
                    throw new Exception();
                else
                    personal = new Alumno(parametros.get(0), parametros.get(1), parametros.get(2),
                            parametros.get(3), parametros.get(4), parametros.get(5),
                            Integer.parseInt(parametros.get(6)));
                break;
            case "PDI":
                personal = new PDI(parametros.get(0), parametros.get(1), parametros.get(2),
                        parametros.get(3),
                        parametros.get(4), parametros.get(5), Integer.parseInt(parametros.get(6)),
                        TCategoria.valueOf(parametros.get(7)));
                break;
            case "PAS":
                personal = new PAS(parametros.get(0), parametros.get(1), parametros.get(2),
                        parametros.get(3),
                        parametros.get(4), parametros.get(5), Integer.parseInt(parametros.get(6)),
                        Integer.parseInt(parametros.get(7)));
                break;
        }

        return personal;
    }
}
