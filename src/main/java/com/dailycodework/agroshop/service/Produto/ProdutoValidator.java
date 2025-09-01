package com.dailycodework.agroshop.service.Produto;

import org.springframework.stereotype.Component;

import com.dailycodework.agroshop.model.Produto;
import com.dailycodework.agroshop.repository.ProdutoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProdutoValidator {
    
    private final ProdutoRepository repository;

    public void validarCriacaoProduto(Produto produto){ 
        if(repository.existsByNomeAndCategoria(produto.getMarca(), produto.getNome())){
            //throw new "Exception que representa ja existir essa entidade"
        }
    }

    public void validarAtualizacaoProduto(Produto produto, Long id){
       
    }
}
