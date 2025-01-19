package br.com.davi;

import br.com.davi.model.Pessoa;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
import java.io.IOException;

public class Escrita {
    public static void main(final String[] args) throws JsonProcessingException {
//        Pessoa pessoaA = new Pessoa("Davi");
        ObjectMapper om = new ObjectMapper();

//        String json = om.writeValueAsString(pessoaA);

        //Apenas sobrescrevendo
        try {
            FileWriter fw = new FileWriter("pessoas.json");
//            fw.write(json);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
