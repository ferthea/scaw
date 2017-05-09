package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unlam.diit.scaw.daos.impl.TareaDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Tarea;
import ar.edu.unlam.diit.scaw.services.TareaService;
import ar.edu.unlam.diit.scaw.services.impl.TareaServiceImpl;


@ManagedBean(name="tareaBean",eager=true)
@SessionScoped
public class TareaBean implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	TareaService tareaService = (TareaService) new TareaServiceImpl();
	
	public List<Tarea> getTareasPublicas(){
		return tareaService.getTareasPublicas();
	}
	
	private String prueba = "A VER AHROA";
	
	public String getPrueba(){
		return this.prueba;
	}
	
	public void setPrueba(String str){
		this.prueba = str;
	}
	
	/*public List<Tarea> getTareaPublicas(){
		return tdao.findAll();
	}*/
	
}
