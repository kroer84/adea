package com.java.examen.Service;

import java.time.LocalDate;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.java.examen.DTO.LoginRequestDTO;
import com.java.examen.DTO.LoginResponseDTO;
import com.java.examen.config.SHAUtil;
import com.java.examen.dao.IUsuarioDao;
import com.java.examen.dominio.Usuario;

@Service
public class LoginService {

    private final IUsuarioDao dao;
    private final ModelMapper mapper;

    public LoginService(IUsuarioDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
		Optional<Usuario> optUsuario = dao.findByLogin(dto.getLogin());

		if (optUsuario.isEmpty()) {
			return new LoginResponseDTO(false, "Usuario no existe", null, null);
		}

		Usuario usuario = optUsuario.get();

		// Validar contrasena encriptada con SHA Base64
		if (!SHAUtil.verificar(dto.getPassword(), usuario.getPassword())) {
			// Incrementar intentos fallidos
			usuario.setIntentos(usuario.getIntentos() + 1);
			dao.save(usuario);
			return new LoginResponseDTO(false, "Contrasena incorrecta", null, null);
		}

		// Validar fecha de caducidad
		if (usuario.getFechaVigencia() != null && LocalDate.now().isAfter(usuario.getFechaVigencia())) {
			return new LoginResponseDTO(false, "La fecha de caducidad ha vencido", null, null);
		}

		// Validar status del usuario
		if (!"A".equals(usuario.getStatus())) {
			return new LoginResponseDTO(false, "Usuario no esta activo. Status: " + usuario.getStatus(), null, null);
		}

		// Login exitoso - actualizar fecha de modificacion
		usuario.setFechaModificacion(LocalDate.now());
		usuario.setIntentos(0.0);
		dao.save(usuario);

		String nombreCompleto = usuario.getNombre();
		if (usuario.getApellidoPaterno() != null) {
			nombreCompleto += " " + usuario.getApellidoPaterno();
		}
		if (usuario.getApellidoMaterno() != null) {
			nombreCompleto += " " + usuario.getApellidoMaterno();
		}

		return new LoginResponseDTO(true, "Login exitoso", nombreCompleto, usuario.getLogin());
	}
    
}
