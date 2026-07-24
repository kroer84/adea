package com.java.examen.DTO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioRequestDTO {
    @NotBlank(message = "El login es obligatorio")
	private String login;

	@NotBlank(message = "El password es obligatorio")
	private String password;

	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;

	@NotNull(message = "El cliente es obligatorio")
	private Double cliente;

	private String email;

	@NotBlank(message = "El status es obligatorio")
	private String status;

	private LocalDate fechaVigencia;

	private String apellidoPaterno;

	private String apellidoMaterno;

	private Integer area;
}
