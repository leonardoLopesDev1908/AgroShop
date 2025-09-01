package com.dailycodework.agroshop.service.Produto;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dailycodework.agroshop.model.Produto;
import com.dailycodework.agroshop.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService implements IProdutoService{

    private final ProdutoRepository repository;

    @Override
    public Produto addProduto(Produto produto) {
        //validator.validar(produto);
        return repository.save(produto);
    }

    @Override
    public Produto buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Nenhum produto encontrado com esse ID");
        });
    }

    @Override
    public Produto atualizarProduto(Produto produto, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizarProduto'");
    }

    @Override
    public void deletarProdutoPorId(Long id) {
        
    }

    @Override
    public List<Produto> getAllProdutos() {
        return repository.findAll();
    }

    @Override
    public List<Produto> getProdutoPorMarcaECategoria(  String categoria, String marca) {
        return repository.findByCategoriaAndMarca(categoria, marca);
    }

    @Override
    public List<Produto> getProdutoPorMarcarENome(String marca, String nome) {
        return repository.findByMarcaAndNome(marca, nome);
    }

    @Override
    public List<Produto> getProdutoPorNome(String nome) {
        return repository.findByNomeContaining(nome);
    }

    @Override
    public List<Produto> getProdutoPorMarca(String marca) {
        return repository.findByMarcaContaining(marca);
    }

    @Override
    public List<Produto> getProdutoPorCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }
    
}
