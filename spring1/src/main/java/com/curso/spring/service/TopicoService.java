package com.curso.spring.service;

import com.curso.spring.model.dto.TopicoDetalheDto;
import com.curso.spring.model.dto.TopicoDto;
import com.curso.spring.model.entity.TopicoEntity;
import com.curso.spring.model.form.TopicoForm;
import com.curso.spring.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public TopicoEntity insert(TopicoForm pTopico) {

        TopicoEntity topico = new TopicoEntity(pTopico, cursoService);

        topicoRepository.save(topico);

        return topico;
    }

    public TopicoDetalheDto carregar(Long pIdTopico) {

        return new TopicoDetalheDto(topicoRepository.getById(pIdTopico));
    }
}
