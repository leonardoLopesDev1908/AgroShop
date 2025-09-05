package com.dailycodework.agroshop.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.dailycodework.agroshop.controller.dto.cadastro.ImagemCadastroDTO;
import com.dailycodework.agroshop.controller.dto.pesquisa.ImagemPesquisaDTO;
import com.dailycodework.agroshop.model.Imagem;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ImagemMapper {
    
    Imagem toEntity(ImagemCadastroDTO dto);
    ImagemPesquisaDTO toDTO(Imagem imagem);
}
