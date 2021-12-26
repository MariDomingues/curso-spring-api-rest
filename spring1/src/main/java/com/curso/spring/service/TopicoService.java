package com.curso.spring.service;

import com.curso.spring.model.dto.TopicoDetalheDto;
import com.curso.spring.model.dto.TopicoDto;
import com.curso.spring.model.entity.TopicoEntity;
import com.curso.spring.model.form.TopicoInsertForm;
import com.curso.spring.model.form.TopicoUpdateForm;
import com.curso.spring.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

    public TopicoDetalheDto carregar(Long pIdTopico) {

        return new TopicoDetalheDto(topicoRepository.getById(pIdTopico));
    }

    public TopicoEntity update(Long pIdTopico, TopicoUpdateForm pTopico) {

        TopicoEntity topico = pTopico.update(pIdTopico, topicoRepository);

        /*
            Não precisa chamar um método específico para atualizar as informações,
            ele já irá fazer isso ao final do método chamado automaticamente.
            Porém, precisa colocar a anotação @Transaction para que o JPA commit.
         */
//        topicoRepository.save(topico);

        return topico;
    }
}
