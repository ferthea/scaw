package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.UsuarioService;

@ManagedBean(name = "loginBean", eager = true)
@RequestScoped
public class LoginBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private boolean logged = false;
	
	ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"beans.xml"});
	UsuarioService usuarioService = (UsuarioService) context.getBean("usuarioService");
	private List<Usuario> usuarios = usuarioService.getListaDeUsuarios();
	
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
	
	FacesContext fcontext = FacesContext.getCurrentInstance();
	
	public String login(){
		for(Usuario user : usuarios){
			if(user.getUsername().equals(this.username) && user.getPassword().equals(this.password)){
				if(user.getActivo() == true){
					logged = true;
					FacesContext context2 = FacesContext.getCurrentInstance();
					HttpSession session = (HttpSession) context2.getExternalContext().getSession(true);
					session.setAttribute("user", user);
					return "index?faces-redirect=true";
				}
				FacesMessage msg2 = new FacesMessage("La cuenta se encuentra pendiente de aprobación.", "ERROR MSG");
		        msg2.setSeverity(FacesMessage.SEVERITY_ERROR);
		        FacesContext.getCurrentInstance().addMessage(null, msg2);
		        return "login";
			}
		}
		
		FacesMessage msg = new FacesMessage("Datos incorrectos.", "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "login";
	}
	
	public String logout(){
		FacesContext context2 = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context2.getExternalContext().getSession(true);
		session.removeAttribute("user");
		return "inicio";
	}

}
