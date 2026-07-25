package com.java.examen.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.java.examen.DTO.UsuarioRequestDTO;
import com.java.examen.DTO.UsuarioResponseDTO;
import com.java.examen.config.SHAUtil;
import com.java.examen.dao.IUsuarioDao;
import com.java.examen.dominio.Usuario;
import com.java.examen.exception.DatosInvalidosException;
import com.java.examen.exception.RecursoNoEncontradoException;

@Service
public class UsuariosService {

    private final IUsuarioDao dao;
    private final ModelMapper mapper;

    public UsuariosService(IUsuarioDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    public List<UsuarioResponseDTO> listarTodos() {
		return dao.findAll().stream()
				.map(u -> mapper.map(u, UsuarioResponseDTO.class))
				.collect(Collectors.toList());
	}

    	public UsuarioResponseDTO buscarPorLogin(String login) {
		Usuario usuario = dao.findByLogin(login)
				.orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado: " + login));
		return mapper.map(usuario, UsuarioResponseDTO.class);
	}
    
	public UsuarioResponseDTO guardar(UsuarioRequestDTO dto){
		if(dao.existsByLogin(dto.getLogin())){
			throw new DatosInvalidosException("El login " + dto.getLogin() + "ya existe");
		}

		Usuario usuario = mapper.map(dto, Usuario.class);
		usuario.setPassword(SHAUtil.encriptar(dto.getPassword()));
		usuario.setFechaAlta(LocalDate.now());
		usuario.setFechaModificacion(LocalDate.now());
		usuario.setStatus(dto.getStatus() != null ? dto.getStatus() : "A");
		usuario.setIntentos(0.0);
				
		Usuario guardado = dao.save(usuario);
		
		return mapper.map(guardado, UsuarioResponseDTO.class);
	}
    
}
