package com.dailycodework.agroshop.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.dailycodework.agroshop.controller.dto.cadastro.ImagemDTO;
import com.dailycodework.agroshop.model.Imagem;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ImagemMapper {
    Imagem toEntity(ImagemDTO dto);

    ImagemDTO toDTO(Imagem imagem);
}
