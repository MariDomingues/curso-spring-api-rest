package com.curso.spring.service;

import com.curso.spring.model.entity.UsuarioEntity;
import com.curso.spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioEntity getUsuario(long pIdUsuario) {

        return usuarioRepository.findById(pIdUsuario).get();
    }

    public void save(UsuarioEntity pUsuario) {
        
        usuarioRepository.save(pUsuario);
    }
}