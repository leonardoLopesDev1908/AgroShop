package com.dailycodework.agroshop.service.Pedido;

import java.util.List;
import java.util.UUID;

import com.dailycodework.agroshop.controller.dto.cadastro.PedidoDTO;

public interface IPedidoService {

    PedidoDTO fazerPedido(UUID usuarioId);
    List<PedidoDTO> pedidosUsuario(UUID usuarioId);

}
