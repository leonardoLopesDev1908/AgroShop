package com.dailycodework.agroshop.controller.dto.cadastro;

import jakarta.validation.constraints.NotBlank;

public record CategoriaCadastroDTO (
                @NotBlank(message = "Campo obrigatório")
                String nome
){}
