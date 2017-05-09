package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import ar.edu.unlam.diit.scaw.daos.TareaDao;
import ar.edu.unlam.diit.scaw.entities.Tarea;
import ar.edu.unlam.diit.scaw.entities.Usuario;


public class TareaDaoImpl implements TareaDao{
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	public TareaDaoImpl() {
		super();
	}
	
	@Override
	public void registrarTarea(Tarea tarea, Usuario user) {
		String sql = "INSERT INTO TAREA(NOMBRE, CREADOR_USERNAME, DESCRIPCION, ACCESO, ESTADO) VALUES (:nombre, :creador_username, :descripcion, :acceso, :estado)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nombre", tarea.getNombre());
		params.put("creador_username", user.getUsername());
		params.put("descripcion", tarea.getDescripcion());
		params.put("acceso", tarea.getAcceso());
		params.put("estado", tarea.getEstado());
		jdbcTemplate.update(sql, params);
	}


	public List<Tarea> getListaDeTareasPublicas() {
		String sql = "SELECT * FROM TAREA WHERE ACCESO = :acceso";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("acceso", "PUBLICA");
		List<Tarea> tareas = jdbcTemplate.query(sql, params, new TareaMapper());
		/*List<Tarea> tareas = new ArrayList<Tarea>();
		Tarea tarea1 = new Tarea();
		Tarea tarea2 = new Tarea();
		tarea1.setNombre("pepe");
		tarea2.setNombre("coqui");
		tareas.add(tarea1);
		tareas.add(tarea2);*/
		return tareas;
	}
	
	public List<Tarea> getListaDeTareasPropias(Usuario user){
		String sql = "SELECT * FROM TAREA WHERE CREADOR_USERNAME = '" + user.getUsername() + "'";
		List <Tarea> tareas = jdbcTemplate.query(sql, new TareaMapper());
		return tareas;
	}
	
	public Map<String, String> getListaDeTareasCompartidasConUsuario(Usuario user){
		String sql = "SELECT IDTAREA, MODO FROM TAREA WHERE ID IN (SELECT IDTAREA FROM ACCEDETAREA WHERE IDUSUARIO = '"+ user.getId() +"')";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Map>(){
		    @Override
		    public Map extractData(ResultSet rs) throws SQLException,DataAccessException {
		        HashMap<String,String> mapRet= new HashMap<String,String>();
		        while(rs.next()){
		            mapRet.put(rs.getString("IDTAREA"),rs.getString("MODO"));
		        }
		        return mapRet;
		    }
		});
	}


	@Override
	public void cambiarModoDeCompartirTarea(Tarea tarea) {
		// TODO Auto-generated method stub
		
	}
	
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static final class TareaMapper implements RowMapper<Tarea>
	{
		@Override
		public Tarea mapRow(ResultSet rs, int rowNum) throws SQLException {
			Tarea tarea= new Tarea();
			tarea.setId(rs.getInt("id"));
			tarea.setAcceso(rs.getString("acceso"));
			tarea.setCreador_username(rs.getString("creador_username"));
			tarea.setDescripcion(rs.getString("descripcion"));
			tarea.setNombre(rs.getString("nombre"));
			tarea.setEstado(rs.getString("estado"));
			return tarea;
		}
	}
}
