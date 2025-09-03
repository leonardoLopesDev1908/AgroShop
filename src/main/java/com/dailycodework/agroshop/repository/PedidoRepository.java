package com.dailycodework.agroshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailycodework.agroshop.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    
}
