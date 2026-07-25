package com.java.examen.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.examen.DTO.TableroResponseDTO;
import com.java.examen.DTO.UsuarioResponseDTO;
import com.java.examen.Service.TableroService;

@RestController
@RequestMapping("dashboard")
@CrossOrigin
public class DashboardController {

    private final TableroService service;

    public DashboardController(TableroService service) {
        this.service = service;
    }

    @GetMapping("/tablero")
	public ResponseEntity<TableroResponseDTO> obtenerTablero() {
		return ResponseEntity.ok(service.obtenerTablero());
	}
    
    @GetMapping("/status/{status}")
	public ResponseEntity<List<UsuarioResponseDTO>> buscarPorStatus(@PathVariable String status) {
		return ResponseEntity.ok(service.buscarPorStatus(status));
	}

    @GetMapping("/buscar")
	public ResponseEntity<List<UsuarioResponseDTO>> buscar(
			@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String apellidoPaterno,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) String status) {

		if (status != null && !status.isBlank()) {
			return ResponseEntity.ok(service.buscarPorStatus(status));
		}
		if (nombre != null && !nombre.isBlank()) {
			return ResponseEntity.ok(service.buscarPorNombre(nombre));
		}
		if (apellidoPaterno != null && !apellidoPaterno.isBlank()) {
			return ResponseEntity.ok(service.buscarPorApellidoPaterno(apellidoPaterno));
		}
		if (email != null && !email.isBlank()) {
			return ResponseEntity.ok(service.buscarPorEmail(email));
		}
		return ResponseEntity.ok(service.listarTodos());
	}

}
