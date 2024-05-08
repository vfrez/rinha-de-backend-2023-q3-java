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
        // Get current size of heap in bytes.
        long heapSize = Runtime.getRuntime().totalMemory();

        // Get maximum size of heap in bytes. The heap cannot grow beyond this size.
        // Any attempt will result in an OutOfMemoryException.
        long heapMaxSize = Runtime.getRuntime().maxMemory();

        // Get amount of free memory within the heap in bytes. This size will
        // increase after garbage collection and decrease as new objects are created.
        long heapFreeSize = Runtime.getRuntime().freeMemory();

        log.info("heapFreeSize {}", heapSize);
        log.info("heapMaxSize {}", heapMaxSize);
        log.info("heapFreeSize {}", heapFreeSize);

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
