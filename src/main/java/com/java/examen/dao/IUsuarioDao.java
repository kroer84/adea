package com.java.examen.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.examen.dominio.Usuario;

@Repository
public interface IUsuarioDao extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByLogin(String login);

	Optional<Usuario> findByLoginAndPassword(String login, String password);

	List<Usuario> findByStatusIgnoreCase(String status);

    List<Usuario> findByStatus(String status);

	List<Usuario> findByNombreContainingIgnoreCase(String nombre);

	List<Usuario> findByApellidoPaternoContainingIgnoreCase(String apellidoPaterno);

	List<Usuario> findByEmailContainingIgnoreCase(String email);

	boolean existsByLogin(String login);

    @Query("SELECT u FROM Usuario u WHERE " +
        "(:status IS NULL OR u.status = :status) AND " +
        "(:nombre IS NULL OR LOWER(u.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) AND " +
        "(:login IS NULL OR LOWER(u.login) LIKE LOWER(CONCAT('%', :login, '%')))")
    List<Usuario> buscarConFiltros(@Param("status") String status,
                                    @Param("nombre") String nombre,
                                    @Param("login") String login);
}
