package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.unlam.diit.scaw.daos.UsuarioDao;
import ar.edu.unlam.diit.scaw.daos.impl.UsuarioDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {

	
	@Autowired
	//UsuarioDao usuarioDao;
	
	UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
	
	@Override
	public List<Usuario> getListaDeUsuarios() {
		return usuarioDao.getUsuarios();
	}
	
	
	public Usuario getUsuarioPorNombre(String nombreUsuario) {
		System.out.println("Usuario a buscar -->" + nombreUsuario);
		try{
			Usuario user = usuarioDao.getUsuario(nombreUsuario);
			return user;
		}catch(Exception e){
			System.err.println(e);
		}
		return null;
	}
	 
	@Override
	public void crearUsuario(Usuario usuario) throws Exception {
		
		if(getUsuarioPorNombre(usuario.getNombre()) == null){
			usuarioDao.registrarUsuario(usuario);
		}
		else{
			throw new Exception("Usuario existente");
		}
		
	}
	
	public Boolean usuarioYaExiste(String username){
		List<Usuario> usuarios = this.getListaDeUsuarios();
		for(Usuario user : usuarios){
			if(user.getUsername() == username){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Integer getLogueo(String nombre, String contrasena) throws Exception {//si retorna uno accede
		
		if(getUsuarioPorNombre(nombre).getId() != null){
			Usuario usuario=getUsuarioPorNombre(nombre);
			if(usuario.getActivo()){
				if(usuario.getPassword().equals(contrasena)){
					return 1;
				}
				else{
					throw new Exception("Acesso denegadoPorqueNoEstaAprobao");
				}
			}
			else{
				throw new Exception("Acesso denegado2");
			}
		}
		else{
			throw new Exception("Acesso denegado3");	
		}
	}
	
	@Override
	public List<Usuario> getListaDeUsuariosNoAprobados() {
		return usuarioDao.getUsuariosNoActivos();
	}
	
	@Override
	public Usuario buscarUnUsuarioPorId(Integer id) {
		
		System.out.println("id buscado " + id);
		return usuarioDao.getUsuario(id);
	
	}
	
	/*@Override
	public List<Usuario> buscaUsuariosEnLaBDDConLike(String nombreUsuario,Integer usuarioActual) {
		return  usuarioDao.buscaUsuariosEnLaBDD(nombreUsuario,usuarioActual);
	}*/
	
	@Override
	public List<Usuario> getListaDeParticipantesDeUnaTareaPorIdTarea(Integer idTarea) {
	
		return usuarioDao.getListaDeParticipantesDeUnaTareaPorIdTarea(idTarea);
	}
	
	
	@Override
	public void aprobarUsuario(Usuario usuario) {
		usuarioDao.activarUsuario(usuario);
	}
	
	public void eliminarUnUsuario(Usuario usuario) {
		usuarioDao.eliminarUsuario(usuario);
	}
	
	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDaoImpl usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@Override
	public void eliminarUnUsuario(Integer idUsuario) {
		// TODO Auto-generated method stub
		
	}
}
