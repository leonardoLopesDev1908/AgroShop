package com.dailycodework.agroshop.service.Produto;

import java.util.List;

import com.dailycodework.agroshop.model.Produto;

public interface IProdutoService {
    
    Produto addProduto(Produto produto);

    Produto buscarPorId(Long id);

    Produto atualizarProduto(Produto produto, Long id);

    void deletarProdutoPorId(Long id);

    List<Produto> getAllProdutos();

    List<Produto> getProdutoPorMarcaECategoria(String categoria, String marca);
    
    List<Produto> getProdutoPorMarcarENome(String marca, String nome);
    
    List<Produto> getProdutoPorNome(String nome);
    
    List<Produto> getProdutoPorMarca(String marca);
    
    List<Produto> getProdutoPorCategoria(String categoria);
}
