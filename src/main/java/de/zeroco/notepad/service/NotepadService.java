package de.zeroco.notepad.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.zeroco.notepad.dao.NotepadDao;

@Service
public class NotepadService {
	
	@Autowired
	NotepadDao notepadDao;
	
	public String save(Map<String, Object> note) {
		if (notepadDao.save(note) > 0) {
			return "Saved Successfully";
		} 
		return null;
	}
	
	public Map<String, Object> getById(int id) {
	    return notepadDao.getById(id);
	}
	
	public List<Map<String, Object>> get() {
		return notepadDao.get();
	}
	
	public int delete(int id) {
		if (notepadDao.delete(id) > 0) {
			return 1;
		} 
		return 0;
	}
}
