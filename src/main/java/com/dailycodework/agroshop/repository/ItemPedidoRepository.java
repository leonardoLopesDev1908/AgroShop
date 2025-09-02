package com.dailycodework.agroshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dailycodework.agroshop.model.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{
    
    List<ItemPedido> findByProdutoId(Long id);
}
