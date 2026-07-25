package com.java.examen.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.java.examen.DTO.TableroResponseDTO;
import com.java.examen.DTO.UsuarioResponseDTO;
import com.java.examen.dao.IUsuarioDao;

@Service
public class TableroService {

    private final IUsuarioDao dao;
	private final ModelMapper mapper;

    public TableroService(IUsuarioDao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

	public TableroResponseDTO obtenerTablero() {
		long activos = dao.findByStatusIgnoreCase("A").size();
		long inactivos = dao.findByStatusIgnoreCase("B").size();
		long revocados = dao.findByStatusIgnoreCase("R").size();
		long total = dao.count();

		TableroResponseDTO tablero = new TableroResponseDTO();
		tablero.setActivos(activos);
		tablero.setInactivos(inactivos);
		tablero.setRevocados(revocados);
		tablero.setTotal(total);
		return tablero;
	}

    public List<UsuarioResponseDTO> buscarPorStatus(String status) {
		return dao.findByStatusIgnoreCase(status).stream()
				.map(u -> mapper.map(u, UsuarioResponseDTO.class))
				.collect(Collectors.toList());
	}

    public List<UsuarioResponseDTO> buscarPorNombre(String nombre) {
		return dao.findByNombreContainingIgnoreCase(nombre).stream()
				.map(u -> mapper.map(u, UsuarioResponseDTO.class))
				.collect(Collectors.toList());
	}

	public List<UsuarioResponseDTO> buscarPorApellidoPaterno(String apellidoPaterno) {
		return dao.findByApellidoPaternoContainingIgnoreCase(apellidoPaterno).stream()
				.map(u -> mapper.map(u, UsuarioResponseDTO.class))
				.collect(Collectors.toList());
	}

	public List<UsuarioResponseDTO> buscarPorEmail(String email) {
		return dao.findByEmailContainingIgnoreCase(email).stream()
				.map(u -> mapper.map(u, UsuarioResponseDTO.class))
				.collect(Collectors.toList());
	}
    
    public List<UsuarioResponseDTO> listarTodos() {
		return dao.findAll().stream()
				.map(u -> mapper.map(u, UsuarioResponseDTO.class))
				.collect(Collectors.toList());
	}
}
