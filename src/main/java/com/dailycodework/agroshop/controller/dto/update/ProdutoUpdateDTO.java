package com.dailycodework.agroshop.controller.dto.update;

import java.math.BigDecimal;

import com.dailycodework.agroshop.model.Categoria;

public record ProdutoUpdateDTO (
                String nome,
                String marca,
                BigDecimal preco,
                int estoque,
                String descricao,
                Categoria categoria
){}
