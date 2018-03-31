package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import entities.News;

@Repository
public class NewsDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<News> getItems(int offset, int row_count){
		String sql = "SELECT news.*,name_cat FROM news JOIN categories ON news.id_cat = categories.id ORDER BY news.id LIMIT ?,?";
		return jdbcTemplate.query(sql, new Object[]{offset,row_count},new BeanPropertyRowMapper<News>(News.class));
	}
	
	public int addItem(News objNews){
		String sql = "INSERT INTO news(title,content,created_at,updated_at,id_cat,write_by) VALUES(?,?,?,?,?,?)";
		return jdbcTemplate.update(sql,new Object[]{objNews.getTitle(),objNews.getContent(),objNews.getCreated_at(),objNews.getUpdated_at(),objNews.getId_cat(),objNews.getWrite_by()});
	}
	
	public int editItem(News objNews){
		String sql = "UPDATE news SET title = ?, content = ?, updated_at = ?, id_cat = ?, write_by = ? WHERE id = ?";
		return jdbcTemplate.update(sql,new Object[]{objNews.getTitle(),objNews.getContent(),objNews.getUpdated_at(),objNews.getId_cat(),objNews.getWrite_by(),objNews.getId()});
	}
	
	public int delItem(int id){
		String sql = "DELETE FROM news WHERE id = ?";
		return jdbcTemplate.update(sql,new Object[]{id});
	}
	
	public News getItem(int id){
		String sql = "SELECT news.*,name_cat FROM news JOIN categories ON news.id_cat = categories.id WHERE news.id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{id},new BeanPropertyRowMapper<News>(News.class));
	}

	public int countItems() {
		String sql = "SELECT COUNT(*) FROM news";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}