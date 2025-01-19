import model.Pessoa;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AgrupandoStreams {
    public static void main(String[] args) {

        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("Davi", 1.67, "2000-09-30"));
        pessoas.add(new Pessoa("Antonio", 1.73, "2000-10-30"));
        pessoas.add(new Pessoa("Arthur", 1.93, "2000-02-10"));
        pessoas.add(new Pessoa("Lucas", 1.85, "2001-05-01"));
        pessoas.add(new Pessoa("Jorge", 1.68, "2001-01-02"));
        pessoas.add(new Pessoa("Carlos", 1.80, "2001-07-03"));
        pessoas.add(new Pessoa("Peter", 1.74, "1995-02-10"));
        pessoas.add(new Pessoa("Mario", 1.64, "1995-03-20"));
        pessoas.add(new Pessoa("Cezar", 1.94, "1995-05-15"));
        pessoas.add(new Pessoa("Augusto", 1.84, ""));

        Map<Integer, Double> alturasPorAno = pessoas.stream()
                .filter(p -> p.getNascimento() != null && !p.getAltura().equals(Double.valueOf("0")))
                .collect(Collectors.groupingBy(p -> p.getNascimento().getYear(),
                        Collectors.averagingDouble(Pessoa::getAltura)));

    DoubleSummaryStatistics est = pessoas.stream()
                .filter(p -> p.getNascimento() != null && !p.getAltura().equals(Double.valueOf("0")))
                .collect(Collectors.summarizingDouble(Pessoa::getAltura));

        System.out.println(alturasPorAno);
        System.out.println("Média de alturas: " + est.getAverage());
        System.out.println("Máxima altura: " + est.getMax());
        System.out.println("Mínima altura: " + est.getMin());
        System.out.println("Quantidade de pessoas válidas: " + est.getCount());
    }
}
