package com.dailycodework.agroshop.service.Categoria;

import java.util.List;

import com.dailycodework.agroshop.model.Categoria;

public interface ICategoraService {
    
    Categoria addCategoria(Categoria categoria);
    Categoria updateCategoria(Categoria categoria, Long id);
    void deleteCategoria(Long id);
    List<Categoria> getAllCategorias();
    Categoria buscaPorNome(String nome);
    Categoria buscaPorId(Long id);
}
