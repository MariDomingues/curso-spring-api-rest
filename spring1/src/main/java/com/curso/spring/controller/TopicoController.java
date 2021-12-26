package com.curso.spring.controller;

import com.curso.spring.model.dto.TopicoDetalheDto;
import com.curso.spring.model.dto.TopicoDto;
import com.curso.spring.model.entity.TopicoEntity;
import com.curso.spring.model.form.TopicoInsertForm;
import com.curso.spring.model.form.TopicoUpdateForm;
import com.curso.spring.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("topico")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping
    //URL: localhost:8080/topico?page=0&size=10&sort=id,asc&sort=dataCriacao,asc
    //RequestParam indica que é um parâmetro obrigatório e ele vem pela URL
    public Page<TopicoDto> lista(@RequestParam(required = false) String pNomeCurso,
                                 //caso não venha nenhuma ordenação, ele vai ordenar o PageableDefault
                                 @RequestParam @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pPageable) {

        return topicoService.consultar(pNomeCurso, pPageable);
    }

    @GetMapping("/{id}")
    //URL: localhost:8080/topico?id=1
    public ResponseEntity<TopicoDetalheDto> carregar(@PathVariable("id") Long pIdTopico) {

        return topicoService.carregar(pIdTopico);
    }

    @PostMapping
    @Transactional
    //RequestBody indica que o parâmetro irá vir no corpo da requisição
    public ResponseEntity<TopicoDto> insert(@RequestBody @Valid TopicoInsertForm pTopico,
                                            UriComponentsBuilder uriComponentsBuilder) {

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