package iwsit2106.myupmclassroom.model;

import java.util.List;

public interface IBDObservador {
	public List<IObservador> obtener();

	public void guardar(List<IObservador> observadores);
}
