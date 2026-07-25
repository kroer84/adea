package com.java.examen.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.java.examen.DTO.UsuarioResponseDTO;
import com.java.examen.dao.IUsuarioDao;
import com.java.examen.dominio.Usuario;
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
    
    
}
