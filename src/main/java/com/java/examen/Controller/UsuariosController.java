package com.java.examen.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.examen.DTO.UsuarioRequestDTO;
import com.java.examen.DTO.UsuarioResponseDTO;
import com.java.examen.Service.UsuariosService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("usuarios")
@CrossOrigin
public class UsuariosController {

    private final UsuariosService service;

    public UsuariosController(UsuariosService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos(){
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{login}")
	public ResponseEntity<UsuarioResponseDTO> buscarPorLogin(@PathVariable String login) {
		return ResponseEntity.ok(service.buscarPorLogin(login));
	}
    
    @PostMapping
	public ResponseEntity<UsuarioResponseDTO> guardar(@Valid @RequestBody UsuarioRequestDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(dto));
	}
    
    @PutMapping("/{login}")
	public ResponseEntity<UsuarioResponseDTO> editar(@PathVariable String login, @Valid @RequestBody UsuarioRequestDTO dto) {
		return ResponseEntity.ok(service.editar(login, dto));
	}

    @DeleteMapping("/{login}")
	public ResponseEntity<Void> eliminar(@PathVariable String login) {
		service.eliminar(login);
		return ResponseEntity.noContent().build();
	}
}
