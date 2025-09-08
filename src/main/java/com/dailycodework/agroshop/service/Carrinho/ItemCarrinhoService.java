package com.dailycodework.agroshop.service.Carrinho;

import org.springframework.stereotype.Service;

import com.dailycodework.agroshop.model.Carrinho;
import com.dailycodework.agroshop.model.ItemCarrinho;
import com.dailycodework.agroshop.model.Produto;
import com.dailycodework.agroshop.repository.CarrinhoRepository;
import com.dailycodework.agroshop.repository.ItemCarrinhoRepository;
import com.dailycodework.agroshop.service.Produto.IProdutoService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemCarrinhoService implements IItemCarrinhoService {

    private final ItemCarrinhoRepository repository;
    private final CarrinhoRepository carrinhoRepository;
    
    private final ICarrinhoService carrinhoService;
    private final IProdutoService produtoService;

    @Override
    public void adicionarItem(Long carrinhoId, Long produtoId, int quantidade) {
        Carrinho carrinho = carrinhoService.buscarCarrinho(carrinhoId);
        Produto produto = produtoService.buscarPorId(produtoId);
        ItemCarrinho item = carrinho.getItems().stream()    
                    .filter(itemCarrinho -> itemCarrinho.getProduto().getId().equals(produtoId))
                    .findFirst().orElse(new ItemCarrinho());
        if(item.getId() == null){
            item.setCarrinho(carrinho);
            item.setProduto(produto);
            item.setQuantidade(quantidade);
        }else{
            item.setQuantidade(item.getQuantidade() + quantidade);
        }
        repository.save(item);
        carrinhoRepository.save(carrinho);
    }

    @Override
    public void removerItem(Long carrinhoId, Long produtoId) {
        Carrinho carrinho = carrinhoService.buscarCarrinho(carrinhoId);
        ItemCarrinho itemCarrinho = carrinho.getItems().stream()
                    .filter(item -> item.getProduto().getId().equals(produtoId))
                    .findFirst().orElseThrow(() -> new EntityNotFoundException("Item não encontrado"));
        carrinho.removeItem(itemCarrinho);
        carrinhoRepository.save(carrinho);
    }

    @Override
    public void atualizarQuantidade(Long carrinhoId, Long produtoId, int novaQuantidade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarQuantidade'");
    }

    @Override
    public ItemCarrinho buscarItens(Long carrinhoId, Long produtoId) {
        Carrinho carrinho = carrinhoService.buscarCarrinho(produtoId);
        return carrinho.getItems().stream()    
                .filter(item -> item.getProduto().getId().equals(produtoId))
                .findFirst().orElseThrow(()-> new EntityNotFoundException("Item não encontrado"));
    }
    
}
