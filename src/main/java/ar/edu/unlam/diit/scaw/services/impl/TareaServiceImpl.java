package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.diit.scaw.daos.impl.TareaDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Tarea;
import ar.edu.unlam.diit.scaw.services.TareaService;

public class TareaServiceImpl implements TareaService{

	TareaDaoImpl tareaDao;
	
	public TareaServiceImpl(){
		tareaDao = new TareaDaoImpl();
	}
	
	public List<Tarea> getTareasPublicas() {
		return tareaDao.getListaDeTareasPublicas();
	}
}
