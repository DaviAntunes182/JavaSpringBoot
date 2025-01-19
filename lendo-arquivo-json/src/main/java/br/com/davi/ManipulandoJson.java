package br.com.davi;

import br.com.davi.model.Endereco;
import br.com.davi.model.Pessoa;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ManipulandoJson {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        File jsonBase = new File("src/main/resources/object.json");
        try {
            Optional<Pessoa> base = objectMapper.readValue(jsonBase, new TypeReference<Optional<Pessoa>>(){});

            if(base.isEmpty()) {
                System.out.println("Vazio");
            }else{
                base.stream().forEach(p-> p.getNome());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}