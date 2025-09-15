package com.dailycodework.agroshop.controller.dto.pesquisa;

import java.time.LocalDateTime;
import java.util.Set;

import com.dailycodework.agroshop.controller.dto.cadastro.ItemPedidoCadastroDTO;

public record PedidoPesquisaDTO(
                    Long id,
                    LocalDateTime data,
                    Set<ItemPedidoCadastroDTO> itens
) {}
