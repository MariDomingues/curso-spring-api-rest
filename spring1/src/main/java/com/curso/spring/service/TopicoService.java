package com.curso.spring.service;

import com.curso.spring.model.dto.TopicoDetalheDto;
import com.curso.spring.model.dto.TopicoDto;
import com.curso.spring.model.entity.TopicoEntity;
import com.curso.spring.model.form.TopicoInsertForm;
import com.curso.spring.model.form.TopicoUpdateForm;
import com.curso.spring.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoService cursoService;

    public List<TopicoDto> consultar(String pNomeCurso) {

        List<TopicoEntity> vTopico = new ArrayList<>();
        if (pNomeCurso == null) {

            vTopico = topicoRepository.findAll();

        } else {
            vTopico = topicoRepository.findByCurso_Nome(pNomeCurso);
        }

        return TopicoDto.converter(vTopico);
    }

    public TopicoEntity insert(TopicoInsertForm pTopico) {

        TopicoEntity topico = new TopicoEntity(pTopico, cursoService);

        topicoRepository.save(topico);

        return topico;
    }

    public ResponseEntity<TopicoDetalheDto> carregar(Long pIdTopico) {

        Optional<TopicoEntity> topicoConsulta = topicoRepository.findById(pIdTopico);

        if (topicoConsulta.isPresent()) {

            return ResponseEntity.ok(new TopicoDetalheDto(topicoConsulta.get()));
        }

        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<TopicoDto> update(Long pIdTopico, TopicoUpdateForm pTopico) {

        /*
            Não precisa chamar um método específico para atualizar as informações,
            ele já irá fazer isso ao final do método chamado automaticamente.
            Porém, precisa colocar a anotação @Transaction para que o JPA commit.
         */
//        topicoRepository.save(topico);

        Optional<TopicoEntity> topicoConsulta = topicoRepository.findById(pIdTopico);

        if (topicoConsulta.isPresent()) {
            TopicoEntity topico = pTopico.update(topicoConsulta.get());

            return ResponseEntity.ok(new TopicoDto(topicoConsulta.get()));
        }

        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity delete(Long pIdTopico) {

        Optional<TopicoEntity> topicoConsulta = topicoRepository.findById(pIdTopico);

        if (topicoConsulta.isPresent()) {
            topicoRepository.deleteById(pIdTopico);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
