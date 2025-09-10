package com.dailycodework.agroshop.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailycodework.agroshop.model.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{
    Carrinho findByUsuarioEmail(String email);
    Carrinho findByUsuarioId(UUID id);
}
