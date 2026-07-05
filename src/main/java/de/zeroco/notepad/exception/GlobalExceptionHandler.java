package de.zeroco.notepad.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handles "no row found" from any queryForMap / queryForObject
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(EmptyResultDataAccessException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", "Record not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

   // Handles any other DB error (bad SQL, constraint violation, connection issue)
   @ExceptionHandler(DataAccessException.class)
   public ResponseEntity<Map<String, Object>> handleDataAccess(DataAccessException ex) {
       Map<String, Object> response = new HashMap<>();
       response.put("success", false);
       response.put("error", "Database error occurred");
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
   }

   // Catches absolutely anything else
   @ExceptionHandler(Exception.class)
   public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
       Map<String, Object> response = new HashMap<>();
       response.put("success", false);
       response.put("error", "Something went wrong");
       return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
   }
}
