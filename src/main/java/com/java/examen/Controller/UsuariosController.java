package com.java.examen.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.examen.DTO.UsuarioResponseDTO;
import com.java.examen.Service.UsuariosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
    
    
}
