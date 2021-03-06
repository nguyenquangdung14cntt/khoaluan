package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.Contact;

@Repository
public class ContactsDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Contact> getItems(int offset, int row_count){
		String sql = "SELECT contacts.*,firstname,lastname,klass FROM contacts JOIN users ON contacts.id_user = users.id ORDER BY id LIMIT ?,?";
		return jdbcTemplate.query(sql, new Object[] { offset, row_count }, new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	public int delItem(int id){
		String sql = "DELETE FROM contacts WHERE id = ?";
		return jdbcTemplate.update(sql, new Object[]{id});
	}
	public int countItems() {
		String sql = "SELECT COUNT(*) FROM contacts";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	public Contact getItem(int id) {
		String sql = "SELECT contacts.*,firstname,lastname,klass FROM contacts JOIN users ON contacts.id_user = users.id WHERE contacts.id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	public int addItem(Contact objContact) {
		String sql = "INSERT INTO contacts(id_user,content) VALUES (?,?)";
		return jdbcTemplate.update(sql, new Object[]{objContact.getId_user(),objContact.getContent()});
	}
	public int checkSpam(Contact objContact){
		String sql = "SELECT COUNT(id) FROM contacts WHERE id_user = ? AND content = ?";
		return jdbcTemplate.queryForObject(sql,new Object[]{objContact.getId_user(),objContact.getContent()},Integer.class);
	}
	public Object getTotal() {
		String sql = "SELECT COUNT(id) FROM contacts";
		return jdbcTemplate.queryForObject(sql,Integer.class);
	}
}
