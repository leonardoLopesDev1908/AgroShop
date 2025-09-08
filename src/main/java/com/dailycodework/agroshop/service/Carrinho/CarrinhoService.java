package com.dailycodework.agroshop.service.Carrinho;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.dailycodework.agroshop.model.Carrinho;
import com.dailycodework.agroshop.model.Usuario;
import com.dailycodework.agroshop.repository.CarrinhoRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CarrinhoService implements ICarrinhoService {

    private final CarrinhoRepository repository;

    @Override
    public Carrinho buscarCarrinho(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarCarrinho'");
    }

    @Override
    public Carrinho buscarPorEmailUsuario(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarPorEmailUsuario'");
    }

    @Override
    public void limparCarrinho(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'limparCarrinho'");
    }

    @Override
    public Carrinho novoCarro(Usuario usuario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'novoCarro'");
    }

    @Override
    public BigDecimal precoTotal(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'precoTotal'");
    }
    
}
