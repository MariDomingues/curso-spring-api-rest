package com.curso.spring.service;

import com.curso.spring.model.entity.CursoEntity;
import com.curso.spring.repository.CursoRepository;
import org.springframework.stereotype.Service;


@Service
public class CursoService {

    private CursoRepository cursoRepository;

    public CursoEntity findByNome(String pNomeCurso) {

        return cursoRepository.findByNome(pNomeCurso);
    }
}
