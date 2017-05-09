package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioDao {
	
	public void registrarUsuario(Usuario usuario);
	public List<Usuario> getUsuarios();
	public Usuario getUsuario(Integer idUsuario);
	public Usuario getUsuario(String username);
	public List<Usuario> getUsuariosNoActivos();
	public void activarUsuario(Usuario usuario);
	public void eliminarUsuario(Usuario usuario);
	public List<Usuario> getListaDeParticipantesDeUnaTareaPorIdTarea(Integer idTarea);
	
	
	/*public void guardarUsuarioBDD(Usuario usuario) ;
	
	public List<Usuario> getListaUsuariosBDD();
	
	public Usuario getUnUsuarioPorIdBdd(Integer idUsuario);
	
	public List<Usuario> getListaDeUsuariosNoAprobadosBDD();
	
	public void aprobarUsuarioBdd(Usuario usuario);
	
	public void eliminarUnUsuarioBdd(Integer idUsuario);
	
	public void modificarUsuario(Integer idUsuario,String nombreU,String apellidoU,String contrasenaU,Integer estaParobado);
	
	public List<Usuario> getUsuarioPorNombreBdd(String nombreUsuario);
	
	
	public List<Usuario>buscaUsuariosEnLaBDD(String nombreUsuario,Integer idUsuarioActual);
	
	public List<Usuario> getListaDeParticipantesDeUnaTareaPorIdTarea(Integer idTarea);
	*/
	
	
}
