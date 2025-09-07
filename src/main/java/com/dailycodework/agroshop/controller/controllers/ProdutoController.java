package com.dailycodework.agroshop.controller.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodework.agroshop.controller.dto.cadastro.ProdutoCadastroDTO;
import com.dailycodework.agroshop.controller.dto.pesquisa.ProdutoPesquisaDTO;
import com.dailycodework.agroshop.controller.dto.update.ProdutoUpdateDTO;
import com.dailycodework.agroshop.controller.mapper.ProdutoMapper;
import com.dailycodework.agroshop.response.ApiResponse;
import com.dailycodework.agroshop.service.Produto.IProdutoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final IProdutoService service;
    private final ProdutoMapper mapper;

    @GetMapping("/produtos")
    public ResponseEntity<ApiResponse> getAllProdutos(){
        List<ProdutoPesquisaDTO> produtos = service.getAllProdutos().stream()
                    .map(mapper::toDTO)
                    .collect(Collectors.toList()); 
        return ResponseEntity.ok(new ApiResponse("Sucesso!", produtos));
    }

    @GetMapping("/produto/{id}/produto")
    public ResponseEntity<ApiResponse> getProduto(@PathVariable Long id){
        ProdutoPesquisaDTO dto = mapper.toDTO(service.buscarPorId(id));
        return ResponseEntity.ok(new ApiResponse("Sucesso!", dto));
    }

    @GetMapping("/produto/{nome}")
    public ResponseEntity<ApiResponse> getProdutoByNome(@PathVariable String nome){
        List<ProdutoPesquisaDTO> dto = service.getProdutoPorNome(nome).stream() 
                .map(mapper::toDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse("Sucesso!", dto));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ApiResponse> cadastroProduto(@Valid @RequestBody ProdutoCadastroDTO dto){
        ProdutoPesquisaDTO produto = mapper.toDTO(service.addProduto(dto));
        return ResponseEntity.ok(new ApiResponse("Sucesso!", produto));
    }

    @DeleteMapping("/produto/deletar/{id}")
    public ResponseEntity<ApiResponse> deletarProduto(@PathVariable Long id){
        service.deletarProdutoPorId(id);
        return ResponseEntity.ok(new ApiResponse("Deletado!", null));
    }   

    @PutMapping("/produto/atualizar/{id}")
    public ResponseEntity<ApiResponse> atualizaProduto(@PathVariable Long id, ProdutoUpdateDTO dto){
        ProdutoPesquisaDTO novoProduto = mapper.toDTO(service.atualizarProduto(id, dto));
        return ResponseEntity.ok(new ApiResponse("Sucesso!", novoProduto));
    }
}
