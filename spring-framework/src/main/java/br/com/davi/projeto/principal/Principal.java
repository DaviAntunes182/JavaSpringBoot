package br.com.davi.projeto.principal;

import br.com.davi.projeto.model.DadosEpisodio;
import br.com.davi.projeto.model.DadosSerie;
import br.com.davi.projeto.model.DadosTemporada;
import br.com.davi.projeto.service.ApiConsumer;
import br.com.davi.projeto.service.DataConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private ApiConsumer consumoApi = new ApiConsumer();
    private DataConverter conversor = new DataConverter();

    private static final String ENDERECO = "https://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=c070cbb8";

    public void exibirMenu(){

        System.out.printf("""
                Digite o nome da série
                """);
        var nomeSerie = sc.nextLine();
        var url = ENDERECO + nomeSerie.replace(" ", "+") + API_KEY;
        var json = consumoApi.obterDados(url);
        var serie = conversor.obterDados(json, DadosSerie.class);
        System.out.println(serie);

//        System.out.println("Digite a temporada");
//        var numTemporada = sc.nextLine();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= serie.temporadas(); i++) {
            url = ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY;
            json = consumoApi.obterDados(url);
            var temporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(temporada);
        }
        temporadas.forEach(System.out::println);

        for (DadosTemporada temporada : temporadas) {
            List<DadosEpisodio> episodios = temporada.episodios();
            Integer contador = 0;
            for (DadosEpisodio episodio : episodios) {
                contador++;
                System.out.println(episodio.titulo());
            }
            System.out.println(contador);
        }
        System.out.println("******************************* LAMBIDAS *********************************");
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
    }

}
