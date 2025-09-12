package com.dailycodework.agroshop.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@Setter
@Getter
@Table
@NoArgsConstructor
public class ItemPedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantidade;

    private BigDecimal preco;

    private BigDecimal precoTotal;

    @ManyToOne
    @JoinColumn(name="pedido_id")
    @JsonIgnore
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name="produto_id")
    private Produto produto;

    public ItemPedido(Pedido pedido, BigDecimal preco, Produto produto, int quantidade){
        this.pedido = pedido;
        this.preco = preco;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public BigDecimal calcularPrecoTotal(){
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
        this.precoTotal = calcularPrecoTotal();
    }
}
