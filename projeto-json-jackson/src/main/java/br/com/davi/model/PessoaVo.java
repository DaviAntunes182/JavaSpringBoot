package br.com.davi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PessoaVo(
        @JsonAlias("Nome") String nome,
        @JsonAlias("Idade") String idade,
        @JsonAlias("Endereço")List<EnderecoVo> enderecos,
        @JsonAlias("Genero") String genero
        ) {
}
