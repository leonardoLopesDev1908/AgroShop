package com.dailycodework.agroshop.service.Usuario;

import org.springframework.stereotype.Component;

import com.dailycodework.agroshop.model.Usuario;
import com.dailycodework.agroshop.repository.UsuarioRepository;

import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioValidator {
    
    private final UsuarioRepository repository;

    public void validar(Usuario usuario){
        if (repository.existsByEmail(usuario.getEmail())){
            throw new EntityExistsException("Usuario já cadastrado com o email: " + 
                                                    usuario.getEmail()); 
        }
    }

}
