package com.curso.spring.repository;

import com.curso.spring.model.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Long> {

    CursoEntity findByNome(String pNomeCurso);
}
