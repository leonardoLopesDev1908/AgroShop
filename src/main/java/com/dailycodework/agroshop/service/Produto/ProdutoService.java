package com.dailycodework.agroshop.service.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dailycodework.agroshop.controller.dto.cadastro.ProdutoCadastroDTO;
import com.dailycodework.agroshop.controller.dto.update.ProdutoUpdateDTO;
import com.dailycodework.agroshop.controller.mapper.ProdutoMapper;
import com.dailycodework.agroshop.model.Carrinho;
import com.dailycodework.agroshop.model.Categoria;
import com.dailycodework.agroshop.model.ItemCarrinho;
import com.dailycodework.agroshop.model.ItemPedido;
import com.dailycodework.agroshop.model.Produto;
import com.dailycodework.agroshop.repository.CategoriaRepository;
import com.dailycodework.agroshop.repository.ItemCarrinhoRepository;
import com.dailycodework.agroshop.repository.ItemPedidoRepository;
import com.dailycodework.agroshop.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService implements IProdutoService{

    private final ProdutoRepository repository;
    private final ProdutoValidator validator;
    private final ProdutoMapper mapper;
    
    private final CategoriaRepository categoriaRepository;

    private final ItemCarrinhoRepository itemCarrinhoRepository;

    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    public Produto addProduto(ProdutoCadastroDTO dto) {
        Produto produto = mapper.toEntity(dto);
        validator.validarCriacaoProduto(produto);

        Categoria categoria = Optional.ofNullable(categoriaRepository.findByNome(produto.getCategoria().getNome()))
            .orElseGet(() -> {
                Categoria novaCategoria = new Categoria(produto.getCategoria().getNome());
                return categoriaRepository.save(novaCategoria);
            });

        produto.setCategoria(categoria);

        return repository.save(produto);
    }

    @Override
    public Produto buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Nenhum produto encontrado com esse ID");
        });
    }

    @Override
    @Transactional
    public Produto atualizarProduto(Long id, ProdutoUpdateDTO dtoNovoProduto) {
        Produto produtoExistente = repository.findById(id).orElseThrow(() ->{
            throw new EntityNotFoundException("Nenhum produto com esse ID");
        });

        mapper.updateProdutoFromDto(dtoNovoProduto, produtoExistente);

        validator.validarAtualizacaoProduto(produtoExistente, id);

        return repository.save(produtoExistente);
    }

    @Override
    public void deletarProdutoPorId(Long id) {
        repository.findById(id)
                .ifPresentOrElse(produto -> {
                    List<ItemCarrinho> itens = itemCarrinhoRepository.findByProdutoId(id);
                    itens.forEach(item -> {
                        Carrinho carrinho = item.getCarrinho();
                        carrinho.removeItem(item);
                        itemCarrinhoRepository.delete(item);
                    });

                    List<ItemPedido> itensPedido =  itemPedidoRepository.findByProdutoId(id);
                    itensPedido.forEach(item -> {
                        item.setProduto(null);
                        itemPedidoRepository.save(item);
                    });

                    Optional.ofNullable(produto.getCategoria())
                            .ifPresent(categoria -> categoria.getProdutos().remove(produto));
                    produto.setCategoria(null);

                    repository.deleteById(produto.getId());
                }, () -> {
                    throw new EntityNotFoundException("Nenhum produto encontrado com esse ID");
                });
    }

    @Override
    public List<Produto> getAllProdutos() {
        return repository.findAll();
    }

    @Override
    public List<Produto> getProdutoPorMarcaECategoria(String categoria, String marca) {
        throw new RuntimeException("");
        // return repository.findByCategoriaAndMarca(categoria, marca);
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
        throw new RuntimeException("");
        //return repository.findByCategoria(categoria);
    }

    @Override
    public List<Produto> findDistinctProdutodsByNome(){
        List<Produto> produtos = getAllProdutos();
        Map<String, Produto> distintos = produtos.stream()  
            .collect(Collectors.toMap(Produto::getNome, 
                                      produto -> produto, 
                                      (existing, replacement) -> existing));
        return new ArrayList<>(distintos.values());
    }
    
}
