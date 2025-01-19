import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Double> reais = new ArrayList<>();
        reais.add(1.4);
        reais.add(3.7);
        reais.add(2.1);

        List<Integer> numeros = new ArrayList<>();
        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        numeros.add(4);
        numeros.add(5);
        numeros.add(6);

        List<String> nomes = Arrays.asList("Davi", "Livia", "Lucas", "Paulo", "Leandro");
//      Lista((elemento) -> expressão)

//      Gera uma stream nova
        nomes.stream()
                .sorted()
                .forEach(s -> System.out.println(s));

        System.out.println();

//      Operações finais -> Fazem algo com a stream final
        nomes.stream()
                .sorted()
                .limit(3)
                .forEach(s -> System.out.println(s));

        System.out.println();

        nomes.stream()
                .sorted()
                .limit(3)
                .filter(nome -> nome.startsWith("L"))
                .map(n -> n.toUpperCase())
                .map(n -> n.replace('I', 'E'))
                .forEach(s -> System.out.println(s));

        System.out.println();

        reais.stream()
                .filter(r -> r > 2)
                .forEach(System.out::println);

        System.out.println();


//      Collectors -> Lista mutável
        List<Integer> impares = numeros.stream()
                .filter(n -> n % 2 != 0)
                .collect(Collectors.toList());
        System.out.println(impares);
        impares.add(7);

//      toList -> Imutável
        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .toList();
        System.out.println(pares);
        pares.add(8);
    }
}
