package com.dailycodework.agroshop.service.Carrinho;

import com.dailycodework.agroshop.model.ItemCarrinho;

public interface IItemCarrinhoService {
    void adicionarItem(Long carrinhoId, Long produtoId, int quantidade);
    void removerItem(Long carrinhoId, Long produtoId);
    void atualizarQuantidade(Long carrinhoId, Long produtoId, int novaQuantidade);
    ItemCarrinho buscarItens(Long carrinhoId, Long produtoId);
}
