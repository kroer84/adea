package com.java.examen.Service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.java.examen.DTO.TableroResponseDTO;
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
    
}
