package com.curso.spring.classe;

import com.curso.spring.model.entity.CursoEntity;
import com.curso.spring.model.entity.PerfilEntity;
import com.curso.spring.model.entity.TopicoEntity;
import com.curso.spring.model.entity.UsuarioEntity;
import com.curso.spring.model.enums.PerfilUsuario;
import com.curso.spring.model.enums.StatusTopico;
import com.curso.spring.repository.CursoRepository;
import com.curso.spring.repository.PerfilRepository;
import com.curso.spring.repository.TopicoRepository;
import com.curso.spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Component
public class OnApplicationStartUp {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (perfilRepository.findAll().isEmpty()) {

            PerfilEntity perfil = new PerfilEntity();
            perfil.setNomePerfil(PerfilUsuario.USUARIO.getDescricaoRole());

            perfilRepository.save(perfil);

            perfil = new PerfilEntity();
            perfil.setNomePerfil(PerfilUsuario.MODERADOR.getDescricaoRole());

            perfilRepository.save(perfil);
        }

        if (usuarioRepository.findAll().isEmpty()) {

            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setNome("Aluno");
            usuario.setEmail("aluno@email.com");
            usuario.setSenha("123");

            usuarioRepository.save(usuario);

            usuario = new UsuarioEntity();
            usuario.setNome("Moderador");
            usuario.setEmail("moderador@email.com");
            usuario.setSenha("123");

            usuarioRepository.save(usuario);
        }

        if (!perfilRepository.findAll().isEmpty() &&
                !usuarioRepository.findAll().isEmpty()) {

            Optional<UsuarioEntity> usuario = usuarioRepository.findByEmail("aluno@email.com");
            usuario.get().setvPerfil(Arrays.asList(perfilRepository.findByNomePerfil(PerfilUsuario.USUARIO.getDescricaoRole()).get()));

            usuarioRepository.save(usuario.get());

            usuario = usuarioRepository.findByEmail("moderador@email.com");
            usuario.get().setvPerfil(Arrays.asList(perfilRepository.findByNomePerfil(PerfilUsuario.MODERADOR.getDescricaoRole()).get()));

            usuarioRepository.save(usuario.get());
        }

        if (cursoRepository.findAll().isEmpty()) {

            CursoEntity curso = new CursoEntity();
            curso.setNome("Spring Boot");
            curso.setCategoria("Programação");

            cursoRepository.save(curso);

            curso = new CursoEntity();
            curso.setNome("HTML 5");
            curso.setCategoria("Front-end");

            cursoRepository.save(curso);
        }

        if (topicoRepository.findAll().isEmpty()) {

            TopicoEntity topico = new TopicoEntity();
            topico.setTitulo("Dúvida");
            topico.setMensagem("Erro ao criar projeto");
            topico.setDataCriacao(LocalDateTime.now());
            topico.setStatus(StatusTopico.NAO_RESPONDIDO);
            topico.setAutor(usuarioRepository.findAll().stream().findFirst().orElse(new UsuarioEntity()));
            topico.setCurso(cursoRepository.findAll().stream().findFirst().orElse(new CursoEntity()));

            topicoRepository.save(topico);

            topico = new TopicoEntity();
            topico.setTitulo("Dúvida 2");
            topico.setMensagem("Projeto não compila");
            topico.setDataCriacao(LocalDateTime.now().plusDays(2));
            topico.setStatus(StatusTopico.NAO_RESPONDIDO);
            topico.setAutor(usuarioRepository.findAll().stream().findFirst().orElse(new UsuarioEntity()));
            topico.setCurso(cursoRepository.findAll().stream().findFirst().orElse(new CursoEntity()));

            topicoRepository.save(topico);

            topico = new TopicoEntity();
            topico.setTitulo("Dúvida 3");
            topico.setMensagem("Tag HTML");
            topico.setDataCriacao(LocalDateTime.now().plusDays(3));
            topico.setStatus(StatusTopico.NAO_RESPONDIDO);
            topico.setAutor(usuarioRepository.findAll().stream().findFirst().orElse(new UsuarioEntity()));
            topico.setCurso(cursoRepository.findAll().stream().filter(c -> c.getNome().equals("HTML 5")).findFirst().orElse(new CursoEntity()));

            topicoRepository.save(topico);
        }
    }
}
