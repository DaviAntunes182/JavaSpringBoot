import java.util.*;
import java.util.stream.Collectors;

public class Desafios {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(10, 20, 30, 40, 50);

        Optional<Integer> maior = numeros.stream()
                .sorted(Comparator.reverseOrder())
                .findFirst();

        Optional<Integer> maior2 = numeros.stream()
                .max(Integer::compareTo);

        DoubleSummaryStatistics est = numeros.stream()
                        .collect(Collectors.summarizingDouble(Integer::doubleValue));

        Integer mai = numeros.stream()
                .sorted(Comparator.reverseOrder())
                .limit(1).mapToInt(Integer::intValue).sum();

        Integer mai2 = numeros.stream()
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .collect(Collectors.summingInt(Integer::intValue));

        Optional<Integer> maio = numeros.stream()
                        .max(Comparator.comparing(Integer::intValue));

        System.out.println(maior.get());
        System.out.println(maior2.get());
        System.out.println(est.getMax());
        System.out.println(mai);
        System.out.println(mai2);
        System.out.println(maio.get());


        List<String> palavras = Arrays.asList("java", "stream", "lambda", "code");

        Map<Integer, List<String>> agrupadas = palavras.stream()
                .collect(Collectors.groupingBy(p -> p.length()));

        System.out.println(agrupadas);

        List<String> nomes = Arrays.asList("Alice", "Bob", "Charlie");

        String resul = nomes.stream()
                .collect(Collectors.joining(", "));

        System.out.println(resul);

        List<Integer> numeros2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer result = numeros2.stream()
                .filter(n -> n % 2 == 0)
                .limit(4).mapToInt(Integer::intValue).sum();
//              .collect(Collectors.summingInt(Integer::intValue));

        System.out.println(result);


    }
}
