package com.curso.spring.service;

import com.curso.spring.model.entity.UsuarioEntity;
import com.curso.spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    //contém a lógica para validar as credenciais de um cliente que está se autenticando.

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String pUsername) throws UsernameNotFoundException {

        Optional<UsuarioEntity> usuarioConsulta = usuarioRepository.findByEmail(pUsername);

        if (usuarioConsulta.isPresent()) {

            return usuarioConsulta.get();
        }

        throw new UsernameNotFoundException("Dados Inválidos!");
    }
}
