package com.java.examen.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "El login es obligatorio")
	private String login;

	@NotBlank(message = "El password es obligatorio")
	private String password;
}
