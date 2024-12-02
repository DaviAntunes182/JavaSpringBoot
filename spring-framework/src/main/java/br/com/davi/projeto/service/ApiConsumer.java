package br.com.davi.projeto.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConsumer {

    public String obterDados(String endereco) {
        //Cliente
        HttpClient client = HttpClient.newHttpClient();
        //Requisição onde passamos o endereço que queremos 'consumir'
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        //Resposta
        HttpResponse<String> response = null;
        //Tentativa de recebimento da resposta
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Corpo da resposta
        String json = response.body();
        return json;
    }
}
