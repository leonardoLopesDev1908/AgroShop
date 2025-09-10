package com.dailycodework.agroshop.controller.dto.cadastro;

import java.math.BigDecimal;
import java.util.Set;

import com.dailycodework.agroshop.model.ItemPedido;
import com.dailycodework.agroshop.model.enums.PedidoStatus;

public record  PedidoDTO(
                    BigDecimal valor,
                    PedidoStatus status,
                    Set<ItemPedido> itens
) {}
