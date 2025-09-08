package com.dailycodework.agroshop.service.Carrinho;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dailycodework.agroshop.model.Carrinho;
import com.dailycodework.agroshop.model.Usuario;
import com.dailycodework.agroshop.repository.CarrinhoRepository;
import com.dailycodework.agroshop.repository.ItemCarrinhoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CarrinhoService implements ICarrinhoService {

    private final CarrinhoRepository repository;
    private final ItemCarrinhoRepository itemRepository;

    @Override
    public Carrinho buscarCarrinho(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Carrinho não encontrado");
        });
    }

    @Override
    public Carrinho buscarPorIdUsuario(UUID id){
        return repository.findByUsuario(id);
    }

    @Override
    public Carrinho buscarPorEmailUsuario(String email) {
        return repository.findByUsuarioEmail(email);
    }   

    @Override
    public void limparCarrinho(Long id) {
        Carrinho carrinho = repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Carrinho não encontrado");
        });
        itemRepository.deleteAllByIdCarrinho(id);
        carrinho.limpar();
        repository.deleteById(id);
    }

    @Override
    public Carrinho novoCarro(Usuario usuario) {
        return Optional.ofNullable(buscarPorIdUsuario(usuario.getId())).orElseGet(() -> {
            Carrinho carrinho = new Carrinho();
            carrinho.setUsuario(usuario);
            return repository.save(carrinho);
        });
    }

    @Override
    public BigDecimal precoTotal(Long id) {
        Carrinho carrinho = repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Carrinho não encontrado");
        });
        return carrinho.getValorTotal();
    }
    
}
