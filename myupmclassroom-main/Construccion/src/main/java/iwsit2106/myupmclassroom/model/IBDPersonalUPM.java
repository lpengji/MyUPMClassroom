package iwsit2106.myupmclassroom.model;

import java.util.List;

public interface IBDPersonalUPM {
	public List<IPersonalUPM> obtener();

	public void guardar(List<IPersonalUPM> personal);
}
