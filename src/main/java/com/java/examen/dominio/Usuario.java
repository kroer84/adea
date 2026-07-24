package com.java.examen.dominio;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "USUARIO")
public class Usuario {

    @Id
	@Column(name = "LOGIN", length = 20)
	private String login;

	@Column(name = "PASSWORD", nullable = false, length = 200)
	private String password;

	@Column(name = "NOMBRE", nullable = false, length = 50)
	private String nombre;

	@Column(name = "CLIENTE", nullable = false)
	private Double cliente;

	@Column(name = "EMAIL", length = 50)
	private String email;

	@Column(name = "FECHAALTA", nullable = false)
	private LocalDate fechaAlta;

	@Column(name = "FECHABAJA")
	private LocalDate fechaBaja;

	@Column(name = "STATUS", nullable = false, length = 1)
	private String status;

	@Column(name = "INTENTOS", nullable = false)
	private Double intentos;

	@Column(name = "FECHAREVOCADO")
	private LocalDate fechaRevocado;

	@Column(name = "FECHA_VIGENCIA")
	private LocalDate fechaVigencia;

	@Column(name = "NO_ACCESO")
	private Integer noAcceso;

	@Column(name = "APELLIDO_PATERNO", length = 50)
	private String apellidoPaterno;

	@Column(name = "APELLIDO_MATERNO", length = 50)
	private String apellidoMaterno;

	@Column(name = "AREA")
	private Integer area;

	@Column(name = "FECHAMODIFICACION", nullable = false)
	private LocalDate fechaModificacion;

    @PrePersist
    public void prePersist() {
        if (this.fechaAlta == null) this.fechaAlta = LocalDate.now();
        if (this.fechaModificacion == null) this.fechaModificacion = LocalDate.now();
        if (this.status == null) this.status = "A";
        if (this.intentos == null) this.intentos = 0.0;
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaModificacion = LocalDate.now();
    }
}
