package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Permissions;

@Repository
public class PermissionDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Permissions> getItems(int id){
		String sql = "SELECT permissions.* FROM permissions LEFT JOIN role_permissions ON permissions.id = role_permissions.id_permission WHERE id_role = ?";
		return jdbcTemplate.query(sql, new Object[]{id},new BeanPropertyRowMapper<Permissions>(Permissions.class));
	}
	public List<Permissions> getItems(){
		String sql = "SELECT * FROM permissions";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Permissions>(Permissions.class));
	}
	public Permissions getItem(String name){
		String sql = "SELECT * FROM permissions WHERE name = ?";
		return jdbcTemplate.queryForObject(sql,new Object[]{name},new BeanPropertyRowMapper<Permissions>(Permissions.class));
	}
	public List<Permissions> getOtherItems(int id) {
		String sql = "SELECT * FROM permissions WHERE id NOT IN(SELECT id_permission FROM role_permissions WHERE id_role = ?) AND name NOT IN('MEMBER','ADMIN')";
		return jdbcTemplate.query(sql, new Object[]{id},new BeanPropertyRowMapper<Permissions>(Permissions.class));
	}
}
