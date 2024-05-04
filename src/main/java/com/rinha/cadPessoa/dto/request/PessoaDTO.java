package com.rinha.cadPessoa.dto.request;

import com.rinha.cadPessoa.validation.annotation.AllListIsString;
import com.rinha.cadPessoa.validation.annotation.ListItemNotNull;
import com.rinha.cadPessoa.validation.annotation.ListItemSize;
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
    @ListItemSize(max = 32)
    @ListItemNotNull()
    private List<Object> stack;

}
