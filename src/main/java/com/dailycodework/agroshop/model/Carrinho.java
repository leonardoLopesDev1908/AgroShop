package com.dailycodework.agroshop.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table
@Data
public class Carrinho {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal valorTotal;

    @OneToOne 
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemCarrinho> items = new HashSet<>();

    public void addItem(ItemCarrinho item){
        this.items.add(item);
        item.setCarrinho(this);
        atualizaPreco();
    }

    public void removeItem(ItemCarrinho item){
        this.items.remove(item);
        item.setCarrinho(null);
        atualizaPreco();
    }

    private void atualizaPreco(){
        this.valorTotal = BigDecimal.ZERO;
        
        for(ItemCarrinho item : items){
            this.valorTotal = this.valorTotal.add(item.getPrecoTotal());
        }
    }
}
