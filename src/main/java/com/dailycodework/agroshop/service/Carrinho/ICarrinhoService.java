package com.dailycodework.agroshop.service.Carrinho;

import java.math.BigDecimal;

import com.dailycodework.agroshop.model.Carrinho;
import com.dailycodework.agroshop.model.Usuario;

public interface ICarrinhoService {
    Carrinho buscarCarrinho(Long id);
    Carrinho buscarPorEmailUsuario(String email);    
    void limparCarrinho(Long id);
    Carrinho novoCarro(Usuario usuario);    
    BigDecimal precoTotal(Long id);
}
