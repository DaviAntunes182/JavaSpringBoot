package br.com.davi.api.model;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class Serie {

    private long id;
    private String titulo;
    private Integer totalTemporadas;
    private Double avaliacao;
    private Genero genero;
    private String atores;
    private String poster;
    private String sinopse;

    public Serie(DadosSerie dadosSerie){
        this.titulo = dadosSerie.titulo();
        this.totalTemporadas = dadosSerie.totalTemporadas();
        this.avaliacao = OptionalDouble.of(Double.valueOf(dadosSerie.avaliacao())).orElse(0);
        this.genero = Genero.fromString(dadosSerie.genero().split(",")[0].trim());
        this.atores = dadosSerie.atores();
        this.poster = dadosSerie.poster();
        this.sinopse = dadosSerie.sinopse();
    }

    public Serie(String titulo, Integer totalTemporadas, String avaliacao, String genero, String atores, String poster, String sinopse) {
        this.titulo = titulo;
        this.totalTemporadas = totalTemporadas;
        this.avaliacao = OptionalDouble.of(Double.valueOf(avaliacao)).orElse(0);
        this.genero = Genero.fromString(genero.split(",")[0].trim());
        this.atores = atores;
        this.poster = poster;
        this.sinopse = sinopse;
    }

    @Override
    public String toString() {
        return String.format("""
                %s Temporadas: %d Avaliação: %.2f
                %s %s
                %s
                """, this.titulo, this.totalTemporadas, this.avaliacao, this.genero, this.sinopse, this.atores);
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public Genero getGenero() {
        return genero;
    }

    public String getAtores() {
        return atores;
    }

    public String getPoster() {
        return poster;
    }

    public String getSinopse() {
        return sinopse;
    }
}
