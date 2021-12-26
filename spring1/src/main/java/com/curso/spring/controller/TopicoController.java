package com.curso.spring.controller;

import com.curso.spring.model.dto.TopicoDetalheDto;
import com.curso.spring.model.dto.TopicoDto;
import com.curso.spring.model.entity.TopicoEntity;
import com.curso.spring.model.form.TopicoInsertForm;
import com.curso.spring.model.form.TopicoUpdateForm;
import com.curso.spring.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("topico")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public List<TopicoDto> lista(String pNomeCurso) {

        return topicoService.consultar(pNomeCurso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoDetalheDto> carregar(@PathVariable("id") Long pIdTopico) {

        return topicoService.carregar(pIdTopico);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> insert(@RequestBody @Valid TopicoInsertForm pTopico, UriComponentsBuilder uriComponentsBuilder) {

        TopicoEntity topico = topicoService.insert(pTopico);

        return ResponseEntity.created(uriComponentsBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri()).body(new TopicoDto(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> update(@PathVariable("id") Long pIdTopico,
                                            @RequestBody @Valid TopicoUpdateForm pTopico) {

        return topicoService.update(pIdTopico, pTopico);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable("id") Long pIdTopico) {

        return topicoService.delete(pIdTopico);
    }
}