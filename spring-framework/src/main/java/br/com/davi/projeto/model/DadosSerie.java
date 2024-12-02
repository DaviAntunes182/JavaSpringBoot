package br.com.davi.projeto.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Ignorar os que não mapeamos
@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSerie (@JsonAlias("Title") String titulo,
                          @JsonAlias("totalSeasons") Integer temporadas,
                          @JsonAlias("imdbRating") String avaliacao){
//@JsonProperty -> Serialização/Desserialização @JsonProperty("imdbRating")
//@JsonAlias -> Desserealização - @JsonAlias("Title", "Title2", ...)
}
