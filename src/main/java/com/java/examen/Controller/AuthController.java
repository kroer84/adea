package com.java.examen.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.examen.DTO.LoginRequestDTO;
import com.java.examen.DTO.LoginResponseDTO;
import com.java.examen.Service.LoginService;
import com.java.examen.Service.UsuariosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
@CrossOrigin
public class AuthController {

	private final LoginService service;

	public AuthController(LoginService service) {
		this.service = service;
	}

	@PostMapping("login")
	public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
		LoginResponseDTO respuesta = service.login(dto);
		if (respuesta.isExito()) {
			return ResponseEntity.ok(respuesta);
		}
		return ResponseEntity.badRequest().body(respuesta);
	}
}