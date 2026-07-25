package com.java.examen.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.examen.DTO.TableroResponseDTO;
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
    

}
