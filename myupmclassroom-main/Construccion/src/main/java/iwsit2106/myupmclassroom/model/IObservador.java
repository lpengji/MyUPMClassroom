package iwsit2106.myupmclassroom.model;

public interface IObservador extends IPersonalUPM {
    public void anadirAula(Aula aula);

    public Aula obtenerAula(int id);

    public void borrarAula(int id);
}
