package com.java.examen.DTO;

import lombok.Data;

@Data
public class TableroResponseDTO {
	private long activos;
	private long inactivos;
	private long revocados;
	private long total;
}
