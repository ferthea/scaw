package ar.edu.unlam.diit.scaw.daos;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.diit.scaw.entities.Tarea;
import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface TareaDao {

	public  void registrarTarea (Tarea tarea, Usuario user);
	public List<Tarea> getListaDeTareasPublicas();
	public List<Tarea> getListaDeTareasPropias(Usuario user);
	public Map<String, String> getListaDeTareasCompartidasConUsuario(Usuario user);
	public void cambiarModoDeCompartirTarea(Tarea tarea);
}
