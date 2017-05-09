package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.UpdatableSqlQuery;

import ar.edu.unlam.diit.scaw.daos.UsuarioDao;
import ar.edu.unlam.diit.scaw.daos.impl.TareaDaoImpl.TareaMapper;
import ar.edu.unlam.diit.scaw.entities.Tarea;
import ar.edu.unlam.diit.scaw.entities.Usuario;

public class UsuarioDaoImpl implements UsuarioDao{
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	public UsuarioDaoImpl(){
		super();
	}
	 
	 public void registrarUsuario(Usuario usuario){
		 String query = "INSERT INTO USUARIO(USERNAME, NOMBRE, APELLIDO, CONTRASENA, TIPO, ACTIVO) VALUES (:username, :nombre, :apellido, :contrasena, :tipo, :activo)";
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("username", usuario.getUsername());
		 params.put("nombre", usuario.getNombre());
		 params.put("apellido", usuario.getApellido());
		 params.put("contrasena", usuario.getPassword());
		 params.put("tipo", usuario.getTipo());
		 params.put("activo", usuario.getActivo());
		 jdbcTemplate.update(query, params);
	 }
	 
	 public List<Usuario> getUsuarios(){
		 String query = "SELECT * FROM USUARIO";
		 Map<String, Object> params = new HashMap<String, Object>();
		 List<Usuario> usuarios = jdbcTemplate.query(query, params, new UsuarioMapper());
		 return usuarios;
	 }
	 
	 public Usuario getUsuario(Integer idUsuario){
		 String query = "SELECT * FROM USUARIO WHERE USUARIO.ID = '"+ idUsuario + "'";
		 Map<String, Object> params = new HashMap<String, Object>();
		 Usuario user = jdbcTemplate.queryForObject(query, params, new UsuarioMapper());
		 return user;
	 }
	 
	 public Usuario getUsuario(String username){
		 String query = "SELECT * FROM USUARIO WHERE USUARIO.USERNAME = '"+ username + "'";
		 Map<String, Object> params = new HashMap<String, Object>();
		 Usuario user = jdbcTemplate.queryForObject(query, params, new UsuarioMapper());
		 return user;
	 }
	 
	 public List<Usuario> getUsuariosNoActivos(){
		 String query = "SELECT * FROM USUARIO WHERE USUARIO.ACTIVO = FALSE";
		 Map<String, Object> params = new HashMap<String, Object>();
		 List<Usuario> usuarios = jdbcTemplate.query(query, params, new UsuarioMapper());
		 return usuarios;
	 }
	 
	 public void activarUsuario(Usuario usuario){
		 String query = "UPDATE USUARIO SET ACTIVO = TRUE WHERE USUARIO.ID = :iduser";
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("iduser", usuario.getId());
		 jdbcTemplate.update(query, params);
	 }
	 
	 public void eliminarUsuario(Usuario usuario){
		 String query = "DELETE FROM USUARIO WHERE USUARIO.ID = :userid";
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("iduser", usuario.getId());
		 jdbcTemplate.update(query, params);
	 }
	 
	 public List<Usuario> getListaDeParticipantesDeUnaTareaPorIdTarea(Integer idTarea){
		 String sql = "SELECT * FROM USUARIO WHERE USUARIO.ID IN (SELECT IDUSUARIO FROM ACCEDETAREA WHERE IDTAREA = :idtarea)";
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("idtarea", idTarea);
		 return jdbcTemplate.query(sql, params, new UsuarioMapper());
	 }
	
	 
	/*obtengo los participantes de una tarea
		public List<Usuario> getListaDeParticipantesDeUnaTareaPorIdTarea(Integer idTarea) {
			Map<String, Object> params = new HashMap<String, Object>();
			String sql = "SELECT USUARIO.ID, USUARIO.NOMBRE ,USUARIO.APELLIDO,USUARIO.CONTRASENA ,USUARIO.TIPO,USUARIO.ESTAAPROBADO FROM (TAREA JOIN ACCEDETAREA  ON TAREA.ID = ACCEDETAREA .IDTAREA) JOIN USUARIO ON ACCEDETAREA.IDUSUARIO = USUARIO.ID  WHERE TAREA.ID = ' " +  " " + idTarea.toString() + " '";
			
			List<Usuario> lista = jdbcTemplate.query(sql, params, new UsuarioMapper());
			return lista;
	
		};*/
	 

	//geters
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}



	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	private static final class UsuarioMapper implements RowMapper<Usuario>{
		
		//@Override
		public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
			Usuario usuario= new Usuario();
			usuario.setId(rs.getInt("id"));
			usuario.setUsername(rs.getString("username"));
			usuario.setNombre(rs.getString("nombre"));
			usuario.setApellido(rs.getString("apellido"));
			usuario.setPassword(rs.getString("contrasena"));
			usuario.setTipo(rs.getString("tipo"));
			usuario.setActivo(rs.getBoolean("activo"));
			return usuario;
		}
		
		
	}

	
	
	
	
	
	
	
}
