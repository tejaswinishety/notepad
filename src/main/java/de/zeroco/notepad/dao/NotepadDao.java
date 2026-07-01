package de.zeroco.notepad.dao;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NotepadDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int save(Map<String, Object> note) {
		return jdbcTemplate.update("INSERT INTO notes (title, content) VALUES (?, ?)", note.get("title"), note.get("content"));
	}
	
	public Map<String, Object> getById(int id) {
	    return jdbcTemplate.queryForMap("SELECT * FROM notes WHERE pk_id = ?", id);
	}
	
	public List<Map<String, Object>> get() {
	    return jdbcTemplate.queryForList("SELECT * FROM notes");
	}
	
	public int delete(int id) {
	    return jdbcTemplate.update("DELETE FROM notes WHERE id = ?", id);
	}
}