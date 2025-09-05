package com.dailycodework.agroshop.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.dailycodework.agroshop.controller.dto.cadastro.CategoriaCadastroDTO;
import com.dailycodework.agroshop.model.Categoria;

@Mapper(componentModel = "spring", 
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {ImagemMapper.class})
public interface CategoriaMapper {
    
    Categoria toEntity(CategoriaCadastroDTO dto);
}
