package com.dailycodework.agroshop.service.Categoria;

import java.util.List;

import com.dailycodework.agroshop.controller.dto.cadastro.CategoriaCadastroDTO;
import com.dailycodework.agroshop.model.Categoria;

public interface ICategoriaService {
    
    Categoria addCategoria(CategoriaCadastroDTO dto);
    Categoria updateCategoria(CategoriaCadastroDTO dto, Long id);
    void deleteCategoria(Long id);
    List<Categoria> getAllCategorias();
    Categoria buscaPorNome(String nome);
    Categoria buscaPorId(Long id);
}
