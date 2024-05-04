package com.rinha.cadPessoa.service;

import com.rinha.cadPessoa.exception.NotNullPropertyException;
import com.rinha.cadPessoa.model.Pessoa;
import com.rinha.cadPessoa.repository.PessoaRepository;
import com.rinha.cadPessoa.dto.request.PessoaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.*;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public String countAllPessoas() {
        return String.valueOf(pessoaRepository.count());
    }

    public Pessoa createPessoa(PessoaDTO pessoaDTO) {
        validateNullableProperties(pessoaDTO);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setApelido(pessoaDTO.getApelido());
        pessoa.setNascimento(pessoaDTO.getNascimento());
        pessoa.setStack(nonNull(pessoaDTO.getStack()) ? pessoaDTO.getStack().toString() : null);

        return pessoaRepository.saveAndFlush(pessoa);
    }

    public Optional<Pessoa> getPessoaById(UUID id) {
        return pessoaRepository.findById(id);
    }

    public List<Pessoa> getPessoaByTerm(String termo) {
        return pessoaRepository.findByTerm(termo);

    }

    private void validateNullableProperties(PessoaDTO pessoaDTO) {
        if (isNull(pessoaDTO.getNome()) || isNull(pessoaDTO.getApelido()) || isNull(pessoaDTO.getNascimento())) {
            throw new NotNullPropertyException("Propriedade obrigatória não informada");
        }
    }
}
