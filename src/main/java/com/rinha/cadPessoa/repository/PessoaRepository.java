package com.rinha.cadPessoa.repository;

import com.rinha.cadPessoa.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    @Query("SELECT p FROM Pessoa p" +
            " WHERE p.apelido LIKE %:term%" +
            " OR p.nome LIKE %:term%" +
            " OR p.stack LIKE %:term%")
    List<Pessoa> findByTerm(@Param("term") String term);


}