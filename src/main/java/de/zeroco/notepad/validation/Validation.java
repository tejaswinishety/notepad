package de.zeroco.notepad.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import de.zeroco.notepad.util.Util;

public class Validation {
	
	public static Map<String, Object> verifyInfo(Map<String, Object> note) {
		List<Map<String, Object>> errors = new ArrayList<>();
		Map<String, Object> response = new HashMap<String, Object>();
		if (Util.isBlank(note.get("title")) && Util.isBlank(note.get("content"))) errors.add(Map.of("Required", "Empty note cannot be created"));
		if (Util.hasData(errors)) {
			response.put("success", false);
			response.put("errors", errors);
		}
		return response;
	}
}
