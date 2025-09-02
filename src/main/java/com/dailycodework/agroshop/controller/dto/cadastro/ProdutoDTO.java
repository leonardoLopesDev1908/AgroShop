package com.dailycodework.agroshop.controller.dto.cadastro;

import java.math.BigDecimal;

import com.dailycodework.agroshop.model.Categoria;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(
                @NotBlank(message="Campo obrigatório")
                String nome,
                @NotBlank(message="Campo obrigatório")
                String marca,
                @NotNull(message="Campo obrigatório")
                @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que 0.0")
                BigDecimal preco,
                @NotNull(message="Campo obrigatório")
                @DecimalMin(value = "0", inclusive = false, message = "Estoque deve ser maior que 0.0")
                int estoque,
                @NotBlank(message="Campo obrigatório")
                String descricao,
                @NotNull(message="Campo obrigatório")
                Categoria categoria
) {}
