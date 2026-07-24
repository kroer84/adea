package com.java.examen.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private String login;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String email;
	private Double cliente;
	private String status;
	private LocalDate fechaAlta;
	private LocalDate fechaBaja;
	private Double intentos;
	private LocalDate fechaRevocado;
	private LocalDate fechaVigencia;
	private Integer noAcceso;
	private Integer area;
	private LocalDate fechaModificacion;
}
