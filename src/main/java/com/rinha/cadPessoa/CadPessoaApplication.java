package com.rinha.cadPessoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CadPessoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CadPessoaApplication.class, args);
    }

}
