package com.curso.spring.controller;

import com.curso.spring.model.dto.TopicoDetalheDto;
import com.curso.spring.model.dto.TopicoDto;
import com.curso.spring.model.entity.TopicoEntity;
import com.curso.spring.model.form.TopicoForm;
import com.curso.spring.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
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
    public TopicoDetalheDto carregar(@PathVariable("id") Long pIdTopico) {

        return topicoService.carregar(pIdTopico);
    }

    @PostMapping
    public ResponseEntity<TopicoDto> insert(@RequestBody @Valid TopicoForm pTopico, UriComponentsBuilder uriComponentsBuilder) {

        TopicoEntity topico = topicoService.insert(pTopico);

        return ResponseEntity.created(uriComponentsBuilder
                .path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri()).body(new TopicoDto(topico));
    }
}
