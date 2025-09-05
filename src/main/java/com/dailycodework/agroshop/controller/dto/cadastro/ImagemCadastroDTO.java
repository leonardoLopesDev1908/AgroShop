package com.dailycodework.agroshop.controller.dto.cadastro;

import java.sql.Blob;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ImagemCadastroDTO(
                @NotBlank(message="Campo obrigatório")
                String arquivoNome,
                @NotBlank(message="Campo obrigatório")
                String arquivoTipo,
                @NotNull(message="Campo obrigatório")
                Blob image,
                @NotNull(message="Campo obrigatório")
                String downloadUrl
) {}
