package br.com.davi.api.principal;


import br.com.davi.api.model.*;
import br.com.davi.api.repository.SerieRepository;
import br.com.davi.api.service.ConsumoApi;
import br.com.davi.api.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=c070cbb8";

    private List<DadosSerie> dadosSeries = new ArrayList<>();

    private SerieRepository serieRepository;
    private List<Serie> series = new ArrayList<>();
    private Optional<Serie> serieBusca;

    public Principal(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }


    public void exibeMenu() {
        Integer op = -1;
        while(op != 0){
            var menu = """
                    1 - Buscar séries -> SALVA SERIE DA WEB NO BANCO
                    2 - Buscar episódios
                    3 - Listar séries buscadas na web
                    4 - Listar séries salvas no banco
                    5 - Buscar episódios de séries salvas no banco -> SALVA CADA EPISODIO DA SERIE NO BANCO
                    6 - Buscar série por parte do nome
                    7 - Buscar série por ator
                    8 - Buscar Top 5 séries
                    9 - Buscar séries por categoria
                   10 - Buscar séries por temporadas e avaliação
                   11 - Buscar episódio por trecho
                   12 - Buscar top 5 episódios de uma série
                   13 - Buscar episódios a partir de uma data
                    0 - Sair
                    """;

            System.out.println(menu);
            op = leitura.nextInt();
            leitura.nextLine();

            switch (op) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerieWeb();
                    break;
                case 3:
                    listarSeriesBuscadasWeb();
                    break;
                case 4:
                    listarSeriesBuscadasBD();
                    break;
                case 5:
                    buscarEpisodioPorSerieBD();
                    break;
                case 6:
                    buscarSeriePorTituloBD();
                    break;
                case 7:
                    buscarSeriePorAtorBD();
                    break;
                case 8:
                    buscarTop5Series();
                    break;
                case 9:
                    buscarSeriePorCategoria();
                    break;
                case 10:
                    buscarSeriesPorTemporadaEAvaliacao();
                    break;
                case 11:
                    buscarEpisodioPorTrecho();
                    break;
                case 12:
                    buscarTop5EpisodioPorSerie();
                    break;
                case 13:
                    buscarEpisodiosApartirDe();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }


    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
        serieRepository.save(serie);
//        dadosSeries.add(dados);
        System.out.println(dados);
    }


    private void buscarEpisodioPorSerieWeb(){
        DadosSerie dadosSerie = getDadosSerie();
        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            var json = consumo.obterDados(ENDERECO + dadosSerie.titulo().replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }
    private void listarSeriesBuscadasWeb() {
        List<Serie> series = new ArrayList<>();
        series = dadosSeries.stream().map(d -> new Serie(d)).collect(toList());
        series.stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }
    private void listarSeriesBuscadasBD() {
        series = serieRepository.findAll();
        series
                .stream()
                .sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }
    private void buscarEpisodioPorSerieBD(){
        listarSeriesBuscadasBD();
        System.out.println("Escolha uma série pelo nome: ");
        var nomeSerie = leitura.nextLine();

        Optional<Serie> serie = series.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nomeSerie.toLowerCase()))
                .findFirst();

        if(serie.isPresent()){

            var serieEncontrada = serie.get();
            List<DadosTemporada> temporadas = new ArrayList<>();
            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());
            serieEncontrada.setEpisodios(episodios);
            serieRepository.save(serieEncontrada);
        }else{
            System.out.println("Serie não encontrada");
        }

    }

    private void buscarSeriePorTituloBD() {
        System.out.println("Digite o nome de uma série: ");
        var nomeSerie = leitura.nextLine();
        serieBusca = serieRepository.findByTituloContainingIgnoreCase(nomeSerie);

        if (serieBusca.isPresent()) {
            System.out.println("Dados da série " + serieBusca.get());
        } else {
            System.out.println("Serie não encontrada");
        }
    }
    private void buscarSeriePorAtorBD() {
        System.out.println("Digite o nome de um ator: ");
        var nomeAtor= leitura.nextLine();
        System.out.println("Avaliação mínima: ");
        var avaliacao = leitura.nextDouble();
        List<Serie> seriesEncontradas = serieRepository.findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, avaliacao);

        for (Serie seriesEncontrada : seriesEncontradas) {
            System.out.println(seriesEncontrada);
        }
    }
    private void buscarTop5Series() {
        List<Serie> seriesEncontradas = serieRepository.findTop5ByOrderByAvaliacaoDesc();

        for (Serie seriesEncontrada : seriesEncontradas) {
            System.out.println(seriesEncontrada);
        }
    }
    private void buscarSeriePorCategoria() {
        System.out.println("Digite o gênero da série");
        var generoString = leitura.nextLine();
        Genero genero = Genero.fromPortugues(generoString);

        List<Serie> seriesEncontradas = serieRepository.findByGenero(genero);
        System.out.println("Séries de " + generoString);
        for (Serie seriesEncontrada : seriesEncontradas) {
            System.out.println(seriesEncontrada);
        }
    }

    private void buscarSeriesPorTemporadaEAvaliacao(){
        System.out.println("Filtrar séries até quantas temporadas? ");
        var totalTemporadas = leitura.nextInt();
        leitura.nextLine();
        System.out.println("Com avaliação a partir de que valor? ");
        var avaliacao = leitura.nextDouble();
        leitura.nextLine();
        List<Serie> filtroSeries = serieRepository.seriesPorTemporadaEAvaliacao(totalTemporadas, avaliacao);
        System.out.println("*** Séries filtradas ***");
        filtroSeries.forEach(s ->
                System.out.println(s.getTitulo() + "  - avaliação: " + s.getAvaliacao()));
    }

    private void buscarEpisodioPorTrecho() {
        System.out.println("Digite o nome de um episodio: ");
        var trechoEpisodio = leitura.nextLine();

        List<Episodio> episodiosEncontrados = serieRepository.episodiosPorTrecho(trechoEpisodio);
        episodiosEncontrados.forEach(e ->
                System.out.printf("""
                        Serie: %s, Temporada %s - Episodio %s - %s\n
                        """, e.getSerie().getTitulo(), e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()));
    }


    private void buscarTop5EpisodioPorSerie() {
        buscarSeriePorTituloBD();
        if(serieBusca.isPresent()){
            Serie serie = serieBusca.get();
            List<Episodio> topEpisodios = serieRepository.topEpisodiosPorSerie(serie);

            topEpisodios.forEach(e ->
                    System.out.printf("""
                        Serie: %s, Temporada %s - Episodio %s - %s\n
                        """, e.getSerie().getTitulo(), e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()));
        }
    }

    private void buscarEpisodiosApartirDe() {
        buscarSeriePorTituloBD();
        if(serieBusca.isPresent()) {
            Serie serie = serieBusca.get();
            System.out.println("Digite um ano: ");
            var anoLancamento = leitura.nextInt();
            leitura.nextLine();

            List<Episodio> episodiosAno = serieRepository.episodiosPorSeriePorAno(serie, anoLancamento);
            episodiosAno.forEach(e ->
                    System.out.printf("""
                        Serie: %s, Temporada %s - Episodio %s - %s\n
                        """, e.getSerie().getTitulo(), e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()));

        }
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }


}