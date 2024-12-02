package br.com.davi.projeto;

import br.com.davi.projeto.model.DadosSerie;
import br.com.davi.projeto.service.ApiConsumer;
import br.com.davi.projeto.service.DataConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSemWebApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringSemWebApplication.class, args);
    }

    //Para ter-se uma aplicação que usa commandline precisamos importar
    @Override
    public void run(String... args) throws Exception {
        //Inferência de tipo -> var assume a instância(new)
        var apiConsumer = new ApiConsumer();

        String json = apiConsumer.obterDados("https://www.omdbapi.com/?t=Matrix&apikey=c070cbb8");
        System.out.println(json);

//        json = apiConsumer.obterDados("https://coffee.alexflipnote.dev/W2lmm8tkypw_coffee.jpg");
//        System.out.println(json);
        DataConverter converter = new DataConverter();
        DadosSerie dados = converter.obterDados(json, DadosSerie.class);
        System.out.println(dados);
    }
}