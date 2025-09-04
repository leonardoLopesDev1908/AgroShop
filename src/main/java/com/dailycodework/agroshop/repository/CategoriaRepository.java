package com.dailycodework.agroshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailycodework.agroshop.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

    Categoria findByNome(String nome);
    boolean existsByNome(String nome);
    boolean existsByNomeAndIdNot(String nome, Long id);

}
