package br.com.davi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EnderecoVo(
        @JsonAlias("Titulo") String titulo,
        @JsonAlias("CEP") String cep,
        @JsonAlias("Numero")String numero,
        @JsonAlias("Complemento")String complemento
        ) {
}
