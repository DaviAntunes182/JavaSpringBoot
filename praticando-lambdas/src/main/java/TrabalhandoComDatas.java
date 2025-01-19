import model.Pessoa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TrabalhandoComDatas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("Davi", 1.67, "2000-09-30"));
        pessoas.add(new Pessoa("Lucas", 1.85, "2001-05-01"));
        pessoas.add(new Pessoa("Peter", 1.74, "1995-02-10"));
        pessoas.add(new Pessoa("Augusto", 1.84, ""));

        pessoas.forEach(p -> System.out.println(p.getIdade()));

        System.out.println("Digite um ano qualquer");
        var ano = sc.nextInt();

        LocalDate inicioAno = LocalDate.of(ano, 1, 1);

        //Se o nascimento for nulo gera erro, portanto filtrar os não nulos também
        List<Pessoa> pessoasAposAno = pessoas.stream()
                .filter(e -> e.getNascimento() != null && e.getNascimento().isAfter(inicioAno))
                .collect(Collectors.toList());

        pessoasAposAno.forEach(p -> System.out.println(p.getNome()));


        pessoasAposAno.stream()
                .forEach(e -> System.out.println(e));

    }
}
