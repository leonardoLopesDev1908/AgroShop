package com.dailycodework.agroshop.controller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dailycodework.agroshop.controller.dto.cadastro.ProdutoDTO;
import com.dailycodework.agroshop.service.Produto.ProdutoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @GetMapping
    public String homeProduto(Model model){
        return "a";
    }

    @PostMapping("/save")
    public String cadastroProduto(@RequestBody ProdutoDTO dto, Model model){
        service.addProduto(dto);
        return "a";
    }

    @DeleteMapping("{id}")
    public void deleteProduto(@PathVariable Long id){
        service.deletarProdutoPorId(id);
    }

    @GetMapping("{id}")
    public String getProduto(@PathVariable Long id){
        service.buscarPorId(id);
        return "a";
    }
}
