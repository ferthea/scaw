package ar.edu.unlam.diit.scaw.services;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioService {
	
	public void crearUsuario(Usuario usuario) throws Exception;
	
	public Boolean usuarioYaExiste(String username);
	
	public List<Usuario> getListaDeUsuarios();
	
	public Usuario getUsuarioPorNombre(String nombreUsuario);
	
	public Integer getLogueo(String nombre, String contrasena) throws Exception;
	
	public List<Usuario> getListaDeUsuariosNoAprobados();
	
	public Usuario buscarUnUsuarioPorId(Integer id);
	
	public void aprobarUsuario(Usuario usuario);
	
	public void eliminarUnUsuario(Integer idUsuario);
	
	/*public void modificarUsuario(Integer idUsuario,String nombreU,String apellidoU,String contrasenaU,Integer estaParobado);

	public List<Usuario>buscaUsuariosEnLaBDDConLike(String nombreUsuario,Integer usuarioActual);*/
	
	public List<Usuario> getListaDeParticipantesDeUnaTareaPorIdTarea(Integer idTarea);
	

	
}
