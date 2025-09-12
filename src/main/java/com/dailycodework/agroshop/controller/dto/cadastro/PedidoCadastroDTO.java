package com.dailycodework.agroshop.controller.dto.cadastro;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;

public record  PedidoCadastroDTO(
                    @NotEmpty(message = "O pedido deve conter ao menos um item")
                    Set<ItemPedidoCadastroDTO> itens
) {}
