package br.com.davi;

import br.com.davi.model.Endereco;
import br.com.davi.model.Pessoa;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EscritaDirecionada {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        File jsonFile = new File("src/main/resources/pessoas.json");
        System.out.println(jsonFile);
        try {
            List<Pessoa> pessoas = objectMapper.readValue(jsonFile, new TypeReference<List<Pessoa>>() {
            });
            for (Pessoa pessoa : pessoas) {
                System.out.println("Nome: " + pessoa.getNome());
                List<Endereco> enderecos = pessoa.getEnderecos();
                for (Endereco endereco : enderecos) {
                    System.out.println("    title: " + endereco.getTitulo());
                    System.out.println("    cep: " + endereco.getCep());
                    System.out.println("    complemento: " + endereco.getComplemento());
                    System.out.println();
                }
            }
            String json = objectMapper.writeValueAsString(pessoas);

            try {
                FileWriter fw = new FileWriter("src/main/resources/teste.json");
                fw.write(json);
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}