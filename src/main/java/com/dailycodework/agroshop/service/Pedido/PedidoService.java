package com.dailycodework.agroshop.service.Pedido;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.dailycodework.agroshop.controller.dto.cadastro.PedidoDTO;
import com.dailycodework.agroshop.controller.mapper.PedidoMapper;
import com.dailycodework.agroshop.model.Carrinho;
import com.dailycodework.agroshop.model.ItemPedido;
import com.dailycodework.agroshop.model.Pedido;
import com.dailycodework.agroshop.model.Produto;
import com.dailycodework.agroshop.model.enums.PedidoStatus;
import com.dailycodework.agroshop.repository.PedidoRepository;
import com.dailycodework.agroshop.repository.ProdutoRepository;
import com.dailycodework.agroshop.service.Carrinho.ICarrinhoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService implements IPedidoService{

    private final PedidoRepository repository;
    private final ProdutoRepository produtoRepository;
    private final ICarrinhoService carrinhoService;
    private final PedidoMapper mapper;

    @Override
    public PedidoDTO fazerPedido(UUID usuarioId) {
        Carrinho carrinho = carrinhoService.buscarPorIdUsuario(usuarioId);
        Pedido pedido = criarPedido(carrinho);
        List<ItemPedido> itens = criarItens(pedido, carrinho);
        pedido.setItens(new HashSet<>(itens));
        pedido.setValorTotal(calcularValorTotal(itens));
        
        Pedido pedidoSalvo = repository.save(pedido);
        carrinhoService.limparCarrinho(carrinho.getId());

        return mapper.toDTO(pedidoSalvo);
    }

    @Override
    public List<PedidoDTO> pedidosUsuario(UUID usuarioId) {
        return repository.findByUsuarioId(usuarioId).stream()   
                .map(mapper::toDTO)
                .toList();
    } 
 
    private BigDecimal calcularValorTotal(List<ItemPedido> itens){
        return itens.stream()
                .map(item -> item.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<ItemPedido> criarItens(Pedido pedido, Carrinho carrinho){
        return carrinho.getItems().stream() 
                    .map(item -> {
                        Produto produto = item.getProduto();
                        produto.setEstoque(produto.getEstoque() - item.getQuantidade());
                        produtoRepository.save(produto);
                        return new ItemPedido(
                            pedido,
                            item.getPrecoUnitario(),
                            produto,
                            item.getQuantidade()
                        );
                    }).toList();
    }

    private Pedido criarPedido(Carrinho carrinho){
        Pedido pedido = new Pedido();
        pedido.setUsuario(carrinho.getUsuario());
        pedido.setStatus(PedidoStatus.PENDENTE);
        return pedido;
    }

}
