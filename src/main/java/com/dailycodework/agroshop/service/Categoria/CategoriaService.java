package com.dailycodework.agroshop.service.Categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dailycodework.agroshop.controller.dto.cadastro.CategoriaCadastroDTO;
import com.dailycodework.agroshop.controller.mapper.CategoriaMapper;
import com.dailycodework.agroshop.model.Categoria;
import com.dailycodework.agroshop.repository.CategoriaRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService implements ICategoriaService{
    
    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    @Override
    public Categoria addCategoria(CategoriaCadastroDTO dto) {
        Categoria categoria = mapper.toEntity(dto);
        return Optional.of(categoria).filter(c -> !repository.existsByNome(categoria.getNome()))
            .map(repository::save)
            .orElseThrow(() -> {
                throw new EntityExistsException("Categoria com nome: " + categoria.getNome() + " ja existe");
            });
    }

    @Override
    public Categoria updateCategoria(CategoriaCadastroDTO dto, Long id) {
        Categoria categoria = mapper.toEntity(dto);
        return Optional.ofNullable(buscaPorId(id))
        .filter(c -> !repository.existsByNomeAndIdNot(categoria.getNome(), id))
        .map(categoriaExistente -> {
            categoriaExistente.setNome(categoria.getNome());
            return repository.save(categoriaExistente);
        }).orElseThrow(() -> {
            throw new EntityNotFoundException("Entidade não encontrada");
        });
    }

    @Override
    public void deleteCategoria(Long id) {
        repository.findById(id).ifPresentOrElse(repository :: delete, () -> {
            throw new EntityNotFoundException("Entidade não encontrada");
        });
    }

    @Override
    public List<Categoria> getAllCategorias() {
        return repository.findAll();
    }

    @Override
    public Categoria buscaPorNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public Categoria buscaPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Categoria não encontrada");
        });
    }
}
