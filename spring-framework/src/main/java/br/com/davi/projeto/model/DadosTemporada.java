package br.com.davi.projeto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosTemporada(@JsonAlias("Season") String temporada,
                             @JsonAlias("Episodes") List<DadosEpisodio> episodios
                             ) {
}
