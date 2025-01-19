import model.Produto;

import java.util.*;
import java.util.stream.Collectors;

public class DesafioProduto {
    public static void main(String[] args) {
        List<Produto> produtos = Arrays.asList(
                new Produto("Smartphone", 800.0, "Eletrônicos"),
                new Produto("Notebook", 1500.0, "Eletrônicos"),
                new Produto("Teclado", 200.0, "Eletrônicos"),
                new Produto("Cadeira", 300.0, "Móveis"),
                new Produto("Monitor", 900.0, "Eletrônicos"),
                new Produto("Mesa", 700.0, "Móveis")
        );

        Map<String, List<Produto>> produtosCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria));

        System.out.println(produtosCategoria);

        Map<String, Long> qtdPorCategoria = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria,
                        Collectors.counting()));

        System.out.println(qtdPorCategoria);

        Map<String, Optional<Produto>> maisCarosPorCategoria = produtos.stream()
                .sorted(Comparator.comparing(Produto::getPreco).reversed())
                .collect(Collectors.groupingBy(Produto::getCategoria, Collectors.maxBy(Comparator.comparing(Produto::getPreco))));
        System.out.println(maisCarosPorCategoria);

        List<Produto> maisCarosLista = new ArrayList<>();
        maisCarosPorCategoria.forEach((k, v) -> maisCarosLista.add(v.get()));
        System.out.println(maisCarosLista);

        Map<String, Double> somaPrecos = produtos.stream()
                .collect(Collectors.groupingBy(Produto::getCategoria,
                        Collectors.summingDouble(Produto::getPreco)));
        System.out.println(somaPrecos);
    }
}
