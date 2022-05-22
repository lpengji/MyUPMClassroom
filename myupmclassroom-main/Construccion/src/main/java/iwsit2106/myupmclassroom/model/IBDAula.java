package iwsit2106.myupmclassroom.model;

import java.util.List;

public interface IBDAula {
	public List<Aula> obtener();

	public void guardar(List<Aula> aulas);
}
