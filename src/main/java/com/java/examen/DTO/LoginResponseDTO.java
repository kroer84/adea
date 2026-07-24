package com.java.examen.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {

    private boolean exito;
	private String mensaje;
	private String nombreCompleto;
	private String login;
}
