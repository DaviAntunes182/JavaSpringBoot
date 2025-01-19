package br.com.davi.api;

import br.com.davi.api.model.Serie;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TesteJsonArquivo {
    public static void main(String[] args) {
        try {
            final var json = new FileReader("series.json");
            final var objectMapper = new ObjectMapper();
            System.out.println(json.read());
//            final var serie = objectMapper.readValue(json, Serie.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
