package br.com.davi;

import br.com.davi.model.Course;
import br.com.davi.model.Lesson;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class LeituraVarios {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        // Registrar o módulo JavaTime para suporte a java.time.Instant
        objectMapper.registerModule(new JavaTimeModule());
        File jsonFile = new File("src/main/resources/file.json");
        System.out.println(jsonFile);
        try {
            List<Course> cursos = objectMapper.readValue(jsonFile, new TypeReference<List<Course>>() {});
            for (Course curso : cursos) {
                System.out.println("ID: " + curso.getId());
                System.out.println("Titulo: " + curso.getTitle());
                List<Lesson> disciplinas = curso.getLessons();
                for (Lesson disciplina : disciplinas) {
                    System.out.println();
                    System.out.println("    id: " + disciplina.getId());
                    System.out.println("    title: " + disciplina.getTitle());
                }
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
