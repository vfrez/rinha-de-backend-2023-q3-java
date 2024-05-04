package com.rinha.cadPessoa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "PESSOA")
public class Pessoa {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "ID")
    private UUID id;

    @Column(nullable = false, name = "NOME")
    private String nome;

    @Column(nullable = false, name = "APELIDO")
    private String apelido;

    @Column(nullable = false,name = "NASCIMENTO")
    private LocalDate nascimento;

    @Column(name = "STACK")
    private String stack;

}

