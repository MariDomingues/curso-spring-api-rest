package com.curso.spring.repository;

import com.curso.spring.model.entity.TopicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<TopicoEntity, Long> {

    Page<TopicoEntity> findByCurso_Nome(String pNomeCurso, Pageable pageable);
}
