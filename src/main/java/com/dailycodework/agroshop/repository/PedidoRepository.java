package com.dailycodework.agroshop.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailycodework.agroshop.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    List<Pedido> findByUsuarioId(UUID id);
}
