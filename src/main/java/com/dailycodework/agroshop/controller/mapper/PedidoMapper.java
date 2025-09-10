package com.dailycodework.agroshop.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.dailycodework.agroshop.controller.dto.cadastro.PedidoDTO;
import com.dailycodework.agroshop.model.Pedido;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PedidoMapper {

    Pedido toEntity(PedidoDTO dto);
    PedidoDTO toDTO(Pedido pedido);
}
