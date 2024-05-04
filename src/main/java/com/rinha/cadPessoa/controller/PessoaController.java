package com.rinha.cadPessoa.controller;

import com.rinha.cadPessoa.model.Pessoa;
import com.rinha.cadPessoa.service.PessoaService;
import com.rinha.cadPessoa.dto.request.PessoaDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping(value = "/pessoas", produces = "application/json")
    public ResponseEntity<Pessoa> createPessoa(@Valid @RequestBody PessoaDTO pessoaDTO) {
        Pessoa pessoa = pessoaService.createPessoa(pessoaDTO);
        log.info("Pessoa criada {}", pessoa);

        MultiValueMap<String, String> header = new HttpHeaders();
        header.add("Location", String.format("/pessoas/%s", pessoa.getId()));

        return new ResponseEntity<>(pessoa, header, HttpStatus.CREATED);
    }

    @GetMapping(value = "/pessoas/{id}", produces = "application/json")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable(value = "id") UUID id) {
        Optional<Pessoa> pessoaOptional = pessoaService.getPessoaById(id);

        return pessoaOptional
                .map(pessoa -> ResponseEntity.ok().body(pessoa))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/pessoas", produces = "application/json")
    public ResponseEntity<List<Pessoa>> getPessoaByTerm(@RequestParam(name = "t") String termo) {
        List<Pessoa> pessoaList = pessoaService.getPessoaByTerm(termo);

        return new ResponseEntity<>(pessoaList, HttpStatus.OK);
    }

    @GetMapping(value = "/contagem-pessoas", produces = "application/json")
    public ResponseEntity<String> countAllPessoas() {
        String quantityPessoas = pessoaService.countAllPessoas();

        return new ResponseEntity<>(quantityPessoas, HttpStatus.OK);
    }

}
