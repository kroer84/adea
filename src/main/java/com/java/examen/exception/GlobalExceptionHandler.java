package com.java.examen.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RecursoNoEncontradoException.class)
	public ResponseEntity<Map<String, Object>> manejarRecursoNoEncontrado(RecursoNoEncontradoException ex) {
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("mensaje", ex.getMessage());
		respuesta.put("fecha", LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
	}

	@ExceptionHandler(DatosInvalidosException.class)
	public ResponseEntity<Map<String, Object>> manejarDatosInvalidos(DatosInvalidosException ex) {
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("mensaje", ex.getMessage());
		respuesta.put("fecha", LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> manejarValidaciones(MethodArgumentNotValidException ex) {
		Map<String, Object> errores = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error ->
			errores.put(error.getField(), error.getDefaultMessage())
		);
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("errores", errores);
		respuesta.put("fecha", LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> manejarExcepcionGeneral(Exception ex) {
		Map<String, Object> respuesta = new HashMap<>();
		respuesta.put("mensaje", "Error interno del servidor: " + ex.getMessage());
		respuesta.put("fecha", LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
	}
}
