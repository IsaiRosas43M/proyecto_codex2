package com.adrian.rosasal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adrian.rosasal.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

	//metodo personalizado con sql nativo
    @Query(value="select count(*) from usuarios", nativeQuery = true)
    Integer totalEntidades();
}
