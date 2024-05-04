package com.rinha.cadPessoa;

import com.rinha.cadPessoa.repository.PessoaRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestClass {

    @Autowired
    private PessoaRepository pessoaRepository;


    @PostConstruct
    public void init() {
        log.info("Limpando base");
        pessoaRepository.deleteAllInBatch();
        log.info("Quantidade de itens na base {}", pessoaRepository.findAll().size());

//        Pessoa pessoa = new Pessoa();
//        pessoa.setApelido("Apelido");
//        pessoa.setNome("Nome");
//        pessoa.setNascimento(LocalDate.now());
//        pessoa.setStack("[stack]");
//
//        pessoaRepository.saveAndFlush(pessoa);

    }
}
