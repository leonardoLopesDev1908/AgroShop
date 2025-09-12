package com.dailycodework.agroshop.service.Pedido;

import java.util.List;
import java.util.UUID;

import com.dailycodework.agroshop.controller.dto.cadastro.PedidoCadastroDTO;

public interface IPedidoService {

    PedidoCadastroDTO fazerPedido(UUID usuarioId);
    List<PedidoCadastroDTO> pedidosUsuario(UUID usuarioId);

}
