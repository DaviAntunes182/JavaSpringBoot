package br.com.davi.projeto;

import br.com.davi.projeto.model.DadosTemporada;
import br.com.davi.projeto.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringSemWebApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringSemWebApplication.class, args);
    }

    //Para ter-se uma aplicação que usa commandline precisamos importar
    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.exibirMenu();

    }
}