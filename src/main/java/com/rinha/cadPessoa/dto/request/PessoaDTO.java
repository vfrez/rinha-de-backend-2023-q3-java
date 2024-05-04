package com.rinha.cadPessoa.dto.request;

import com.rinha.cadPessoa.validation.anotation.AllListIsString;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO implements Serializable {

    @Size(max = 32, message = "Apelido maior que 32 caracteres")
    private String apelido;

    @Size(max = 100, message = "Nome maior que 32 caracteres")
    private String nome;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate nascimento;

    //Precisou ser Object. Pq quando passa List<String> o objeto ja chega convertido para string e a validação da ruim.
    @AllListIsString()
    @Size(max = 32)
    private List<Object> stack;

}
