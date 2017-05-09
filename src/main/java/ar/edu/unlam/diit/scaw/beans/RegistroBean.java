package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.UsuarioService;

@ManagedBean(name = "registroBean", eager = true)
@RequestScoped
public class RegistroBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String apellido;
	private String username;
	private String password;
	
	ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
	UsuarioService usuarioService = (UsuarioService) context.getBean("usuarioService");
	
	public RegistroBean(){
		super();
		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String validarDatos(){
		if(!usuarioService.usuarioYaExiste(this.username)){
			this.registrarUsuario();
		}
		return null;
	}
	
	public void registrarUsuario(){
		Usuario user = new Usuario();
		user.setNombre(this.nombre);
		user.setApellido(this.apellido);
		user.setUsername(this.username);
		user.setPassword(this.password);
		try {
			usuarioService.crearUsuario(user);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario creado con exito!"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Usuario> users = usuarioService.getListaDeUsuarios();
		for(Usuario usuario : users){
			System.out.println(usuario.getUsername());
		}
	}
}
