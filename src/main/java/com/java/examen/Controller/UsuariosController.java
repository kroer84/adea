package com.java.examen.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.examen.DTO.UsuarioResponseDTO;
import com.java.examen.Service.UsuariosService;
import org.springframework.web.bind.annotation.GetMapping;


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
    
    
}
