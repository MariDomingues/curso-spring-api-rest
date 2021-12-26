package com.curso.spring.repository;

import com.curso.spring.model.entity.TopicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<TopicoEntity, Long> {

    List<TopicoEntity> findByCurso_Nome(String pNomeCurso);
}
