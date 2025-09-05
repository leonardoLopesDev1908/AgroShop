package com.dailycodework.agroshop.service.Imagem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dailycodework.agroshop.controller.dto.cadastro.ImagemCadastroDTO;
import com.dailycodework.agroshop.controller.dto.pesquisa.ImagemPesquisaDTO;
import com.dailycodework.agroshop.controller.mapper.ImagemMapper;
import com.dailycodework.agroshop.model.Imagem;
import com.dailycodework.agroshop.model.Produto;
import com.dailycodework.agroshop.repository.ImagemRepository;
import com.dailycodework.agroshop.service.Produto.ProdutoService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImagemService implements IImagemService{

    private final ImagemRepository repository;
    private final ProdutoService produtoService;
    private final ImagemMapper mapper;

    @Override
    public Imagem buscarImagemPorId(Long id) {
        return repository.findById(id).orElseThrow(()->{
            throw new EntityNotFoundException("Imagem não encontrada");
        });
    }

    @Override
    public void deletePorId(Long id) {
        repository.findById(id).ifPresentOrElse(repository::delete, () -> {
            throw new EntityNotFoundException("Imagem não encontrada");
        });
    }

    @Override
    public void updateImagem(MultipartFile file, Long id) {
        Imagem imagem = buscarImagemPorId(id);
        try {
            imagem.setArquivoNome(file.getOriginalFilename());
            imagem.setArquivoTipo(file.getContentType());
            imagem.setImage(new SerialBlob(file.getBytes()));
            repository.save(imagem);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<ImagemPesquisaDTO> salvarImagens(Long idProduto, List<MultipartFile> files) {
        Produto produto = produtoService.buscarPorId(idProduto);
    
        List<ImagemPesquisaDTO> imagens = new ArrayList<>();

        for(MultipartFile file : files){
            try {
                Imagem imagem = new Imagem();
                imagem.setArquivoNome(file.getOriginalFilename());
                imagem.setArquivoTipo(file.getContentType());
                imagem.setImage(new SerialBlob(file.getBytes()));
                imagem.setProduto(produto);

                String buildDownloadUrl = "/api/v1/images/imagem/download/";
                String downloadUrl = buildDownloadUrl + imagem.getId();
                imagem.setDownloadUrl(downloadUrl); 

                Imagem imagemSalva = repository.save(imagem);

                imagemSalva.setDownloadUrl(downloadUrl + imagemSalva.getId());
                repository.save(imagemSalva);

                ImagemPesquisaDTO dto = mapper.toDTO(imagemSalva);

                imagens.add(dto);

            } catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return imagens;
    }
    
    
}
