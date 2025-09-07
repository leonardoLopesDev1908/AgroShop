package com.dailycodework.agroshop.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dailycodework.agroshop.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    boolean existsByEmail(String email);
    
    @Query("SELECT u usuario FROM Usuario u " + 
            "WHERE u.nome LIKE %:nome% OR u.sobrenome LIKE %:nome% " + 
            "ORDER BY u.nome")
    List<Usuario> findByNomeOrSobrenome(String nome);
}
