package com.cdental.citas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// EXPLICACIÓN ACADÉMICA: Esta anotación intercepta cualquier excepción lanzada 
// en los controladores. Actúa como un interceptor central de errores para la API.
@RestControllerAdvice
public class ManejadorExcepcionesGlobal {

    // Registra y maneja RuntimeException
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> manejarErrorDeNegocio(RuntimeException ex) {
        
        Map<String, Object> cuerpoError = new HashMap<>();
        cuerpoError.put("timestamp", LocalDateTime.now());
        cuerpoError.put("status", HttpStatus.BAD_REQUEST.value());
        cuerpoError.put("error", "Error en Operación de Citas");
        cuerpoError.put("mensaje", ex.getMessage()); // Aquí viaja tu "Error: El paciente no existe"
        
        // Retornamos 400 (Bad Request)
        return new ResponseEntity<>(cuerpoError, HttpStatus.BAD_REQUEST);
    }
}