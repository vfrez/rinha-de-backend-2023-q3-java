package com.rinha.cadPessoa.controller;

import com.rinha.cadPessoa.dto.request.PessoaDTO;
import com.rinha.cadPessoa.exception.DuplicatedEntryException;
import com.rinha.cadPessoa.model.Pessoa;
import com.rinha.cadPessoa.service.CacheOperationsService;
import com.rinha.cadPessoa.service.PessoaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private CacheOperationsService cacheOperationsService;

    @PostMapping(value = "/pessoas", produces = "application/json")
    public ResponseEntity<Pessoa> createPessoa(@Valid @RequestBody PessoaDTO pessoaDTO) {
        StopWatch stopWatch = StopWatch.createStarted();

        if (cacheOperationsService.countByApelidoInCache(pessoaDTO.getApelido()) > NumberUtils.INTEGER_ZERO){
            throw new DuplicatedEntryException("Duplicado no cache");
        }

        Pessoa pessoa = pessoaService.createPessoa(pessoaDTO);
        log.info("Pessoa criada {}", pessoa);

        cacheOperationsService.storeInCache(pessoa);

        log.info("Tempo de criação {}", stopWatch.formatTime());
        return new ResponseEntity<>(pessoa, createResponseHeader(pessoa), HttpStatus.CREATED);
    }

    @GetMapping(value = "/pessoas/{id}", produces = "application/json")
    public ResponseEntity<Pessoa> getPessoaById(@PathVariable(value = "id") UUID id) {
        StopWatch stopWatch = StopWatch.createStarted();

        Pessoa pessoa = pessoaService.getPessoaById(id);

        log.info("Tempo de busca por ID {}", stopWatch.formatTime());

        if (Objects.isNull(pessoa)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(pessoa);
    }

    @GetMapping(value = "/pessoas", produces = "application/json")
    public ResponseEntity<List<Pessoa>> getPessoaByTerm(@RequestParam(name = "t") String termo) {
        StopWatch stopWatch = StopWatch.createStarted();

        List<Pessoa> byTermInCache = cacheOperationsService.findByTermInCache(termo);

        log.info("Tempo de busca por termo cached {}", stopWatch.formatTime());
        return new ResponseEntity<>(byTermInCache, HttpStatus.OK);

        /*
        Removido pra dar desempenho, assim toda pesquisa é feita apenas no cache em memória

        if (!byTermInCache.isEmpty()) {
            log.info("Tempo de busca por termo cached {}", stopWatch.formatTime());
            log.info("termo cached {}", termo);
            return new ResponseEntity<>(byTermInCache, HttpStatus.OK);
        }
        List<Pessoa> pessoaList = pessoaService.getPessoaByTerm(termo);

        log.info("Tempo de busca por termo {}", stopWatch.formatTime());
        return new ResponseEntity<>(pessoaList, HttpStatus.OK);
        */
    }

    @GetMapping(value = "/contagem-pessoas", produces = "text/plain")
    public ResponseEntity<String> countAllPessoas() {
        String quantityPessoas = pessoaService.countAllPessoas();

        return new ResponseEntity<>(quantityPessoas, HttpStatus.OK);
    }

    @GetMapping(value = "/limparcache", produces = "text/plain")
    public ResponseEntity<String> limparCache() {
        cacheOperationsService.clearCache();

        return new ResponseEntity<>("Limpadinho da balada", HttpStatus.OK);
    }

    private MultiValueMap<String, String> createResponseHeader(Pessoa pessoa) {
        MultiValueMap<String, String> header = new HttpHeaders();
        header.add("Location", String.format("/pessoas/%s", pessoa.getId()));

        return header;
    }

}
