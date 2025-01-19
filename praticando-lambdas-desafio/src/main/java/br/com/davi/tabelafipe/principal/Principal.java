package br.com.davi.tabelafipe.principal;

import br.com.davi.tabelafipe.model.Dados;
import br.com.davi.tabelafipe.model.Modelos;
import br.com.davi.tabelafipe.model.Veiculo;
import br.com.davi.tabelafipe.service.ConsumoApi;
import br.com.davi.tabelafipe.service.ConverteDados;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados converteDados = new ConverteDados();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    public void menu(){
        var menu = """
                *** Opções ***
                Carro
                Moto
                Caminhão
                
                Digite uma das opções
                """;
        System.out.println(menu);
        var op = sc.nextLine();

        String url = URL_BASE;
        if(op.toLowerCase().contains("carr")){
            url += "carros/marcas";
        } else if (op.toLowerCase().contains("mot")) {
            url += "motos/marcas";
        }else {
            url += "caminhoes/marcas";
        }
        var json = consumoApi.obterDados(url);
        System.out.println(json);

        var marcas = converteDados.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca desejada");
        var codMarca = sc.nextLine();

        url += "/" + codMarca + "/modelos";

        //Agora é um novo json, servido pela nova url
        json = consumoApi.obterDados(url);

        //Obtendo uma lista de modelos
        var modeloLista = converteDados.obterDados(json, Modelos.class);

        System.out.println("Modelos desta marca : \n");
        //Uma lista de modelos(dados)
        // Cada modelo é uma lista que é composta por Codigo e Nome do modelo
//
        modeloLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        System.out.println("Digite o modelo do carro: ");
        String modelo = sc.nextLine();

        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(modelo.toLowerCase()))
                .collect(Collectors.toList());

        modelosFiltrados.forEach(System.out::println);

        System.out.println("Escolha um dos modelos acima pelo código para consultar");
        var codigoModelo = sc.nextLine();

        url += "/" + codigoModelo + "/anos";
        json = consumoApi.obterDados(url);

        List<Dados> anos = converteDados.obterLista(json, Dados.class);
        List<Veiculo> veiculos = new ArrayList<>();

        for (int i = 0; i < anos.size(); i++) {
            var enderecosAnos = url + "/" + anos.get(i).codigo();
            json = consumoApi.obterDados(enderecosAnos);
            Veiculo veiculo = converteDados.obterDados(json, Veiculo.class);
            veiculos.add(veiculo);
        }
        System.out.println("Todos os veículos filtrados com avaliações por ano: ");
        veiculos.forEach(System.out::println);
    }
}
