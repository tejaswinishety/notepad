package de.zeroco.notepad.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.zeroco.notepad.service.NotepadService;
import de.zeroco.notepad.util.Util;
import de.zeroco.notepad.validation.Validation;

@RestController
public class NotepadController {
	
	@Autowired
	NotepadService notepadService;
	
	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody Map<String, Object> note) {
		Map<String, Object> errors = Validation.verifyInfo(note);
		if (Util.hasData(errors.get("errors"))) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		String message = notepadService.save(note);
		if (message != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(message);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create");
		}
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Map<String, Object>> getById(@PathVariable int id) {
		Map<String, Object> data = notepadService.getById(id);
		return ResponseEntity.ok(data);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Map<String, Object>>> get() {
		List<Map<String, Object>> data = notepadService.get();
		return ResponseEntity.ok(data);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable int id) {
		int rowsAffected = notepadService.delete(id);
		if (rowsAffected > 0) {
			return ResponseEntity.status(HttpStatus.OK).body("Note deleted successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Note not found");
		}
	}
}
