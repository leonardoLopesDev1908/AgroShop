package com.dailycodework.agroshop.service.Imagem;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dailycodework.agroshop.controller.dto.cadastro.ImagemDTO;
import com.dailycodework.agroshop.model.Imagem;

public interface IImagemService {
    
    Imagem buscarImagemPorId(Long id);
    void deletePorId(Long id);
    void updateImagem(MultipartFile file, Long id);
    List<ImagemDTO> salvarImagens(Long idProduto, List<MultipartFile> files);

}
