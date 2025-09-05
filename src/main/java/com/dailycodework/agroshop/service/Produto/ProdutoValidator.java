package com.dailycodework.agroshop.service.Produto;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.dailycodework.agroshop.model.Produto;
import com.dailycodework.agroshop.repository.ProdutoRepository;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProdutoValidator {
    
    private final ProdutoRepository repository;

    public void validarCriacaoProduto(Produto produto){ 
        validaUnicidade(produto, null);
    }

    public void validarAtualizacaoProduto(Produto produto, Long id){
        validaPreco(produto);
        validaUnicidade(produto, id);
    }

    private void validaPreco(Produto produto){
        if(produto.getPreco() != null && produto.getPreco().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Preço inválido");
        }
    }

    private void validaUnicidade(Produto produto, Long id){
        boolean existeDuplicado = (id != null) ? 
                    repository.existsByNomeAndMarcaAndIdNot(produto.getNome(), produto.getMarca(), id) :
                    repository.existsByNomeAndMarca(produto.getNome(), produto.getMarca());

        if(existeDuplicado){
            throw new EntityExistsException("Produto ja cadastrado");
        }
    }
}
