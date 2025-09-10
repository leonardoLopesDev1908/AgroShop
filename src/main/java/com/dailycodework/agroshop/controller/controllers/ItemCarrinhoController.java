package com.dailycodework.agroshop.controller.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodework.agroshop.model.Carrinho;
import com.dailycodework.agroshop.model.Usuario;
import com.dailycodework.agroshop.response.ApiResponse;
import com.dailycodework.agroshop.service.Carrinho.ICarrinhoService;
import com.dailycodework.agroshop.service.Carrinho.IItemCarrinhoService;
import com.dailycodework.agroshop.service.Usuario.IUsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/itens")
@RequiredArgsConstructor
public class ItemCarrinhoController {
    
    private final IItemCarrinhoService service; 
    private final IUsuarioService usuarioService;
    private final ICarrinhoService carrinhoService;

    @PostMapping("/item/cadastrar")
    public ResponseEntity<ApiResponse> adicionarItemCarrinho(//@RequestParam UUID idUsuario, 
                                                    @RequestParam Long produtoId, @RequestParam int quantidade){
        Usuario usuario = usuarioService.buscarPorId(UUID.fromString("044E0BDA-1A79-46E7-822C-A02573B5F6C9"));
        Carrinho carrinho = carrinhoService.novoCarro(usuario);    
        service.adicionarItem(carrinho.getId(), produtoId, quantidade);
        return ResponseEntity.ok(new ApiResponse("Sucesso!", null));
    }

    @DeleteMapping("/carrinho/{idCarrinho}/item/{produtoId}/excluir")
    public ResponseEntity<ApiResponse> excluirItem(@PathVariable Long idCarrinho, @PathVariable Long produtoId){
        service.removerItem(idCarrinho, produtoId);
        return ResponseEntity.ok(new ApiResponse("Sucesso!", null));
    }

    @PutMapping()
    public ResponseEntity<ApiResponse> atualizarQuantidade(@PathVariable Long idCarrinho, 
                                                           @PathVariable Long produtoId,
                                                           @RequestParam int quantidade){
        service.atualizarQuantidade(idCarrinho, produtoId, quantidade);
        return ResponseEntity.ok(new ApiResponse("Sucesso!", null));
    }
}
