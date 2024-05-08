package com.rinha.cadPessoa.service;

import com.rinha.cadPessoa.dto.request.PessoaDTO;
import com.rinha.cadPessoa.exception.NotNullPropertyException;
import com.rinha.cadPessoa.model.Pessoa;
import com.rinha.cadPessoa.repository.PessoaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public String countAllPessoas() {
        return String.valueOf(pessoaRepository.count());
    }

    public Pessoa createPessoa(PessoaDTO pessoaDTO) {
        validateNullableProperties(pessoaDTO);

        String stack = nonNull(pessoaDTO.getStack()) ? pessoaDTO.getStack().toString() : null;

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setApelido(pessoaDTO.getApelido());
        pessoa.setNascimento(pessoaDTO.getNascimento());
        pessoa.setStack(stack);

        Pessoa pessoaCriada = pessoaRepository.saveAndFlush(pessoa);

        return pessoaCriada;
    }

    @Cacheable("pessoas")
    public Pessoa getPessoaById(UUID id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);

        return pessoa;
    }

    public List<Pessoa> getPessoaByTerm(String termo) {
        List<Pessoa> listPessoa = pessoaRepository.findByTerm(termo);

        return listPessoa;

    }

    private void validateNullableProperties(PessoaDTO pessoaDTO) {
        if (isNull(pessoaDTO.getNome()) || isNull(pessoaDTO.getApelido()) || isNull(pessoaDTO.getNascimento())) {
            throw new NotNullPropertyException("Propriedade obrigatória não informada");
        }
    }
}
