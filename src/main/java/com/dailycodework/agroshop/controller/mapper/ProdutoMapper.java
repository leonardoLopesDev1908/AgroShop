package com.dailycodework.agroshop.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.dailycodework.agroshop.controller.dto.cadastro.ProdutoCadastroDTO;
import com.dailycodework.agroshop.controller.dto.pesquisa.ProdutoPesquisaDTO;
import com.dailycodework.agroshop.controller.dto.update.ProdutoUpdateDTO;
import com.dailycodework.agroshop.model.Produto;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProdutoMapper {

    Produto toEntity(ProdutoCadastroDTO dto);
 
    void updateProdutoFromDto(ProdutoUpdateDTO dto, @MappingTarget Produto produto);

    ProdutoPesquisaDTO toDTO(Produto produto);
}
