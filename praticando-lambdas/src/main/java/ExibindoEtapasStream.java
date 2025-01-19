import model.Pessoa;

import java.util.*;

public class ExibindoEtapasStream {
    public static void main(String[] args) {
//        Função peek
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("Davi", 1.67, "2000-09-30"));
        pessoas.add(new Pessoa("Lucas", 1.85, "2001-05-01"));
        pessoas.add(new Pessoa("Peter", 1.74, "1995-02-10"));
        pessoas.add(new Pessoa("Augusto", 1.84, ""));

        pessoas.stream()
                .filter(p -> p.getNascimento() != null)
                .peek(p -> System.out.println("Primeiro filtro, datas de nascimento informadas " + p))
                .sorted(Comparator.comparing(Pessoa::getIdade).reversed())
                .peek(p -> System.out.println("Ordenação por idade " + p))
                .map(p -> p.getNome().toUpperCase())
                .peek(p -> System.out.println("Utilizando a função to upperCase nos nomes da stream "+ p))
                .forEach(System.out::println);



//        System.out.println(pessoas);

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um ano para busca");
        Integer ano = sc.nextInt();
        sc.nextLine();
        Optional<Pessoa> optAno = pessoas.stream()
                .filter(p -> p.getNascimento()!= null && p.getNascimento().getYear() == ano)
                .findFirst();
        if(optAno.isPresent()){
            Pessoa realAno = optAno.get();
            System.out.println(realAno.getNome());
        }else{
            System.out.println("Ningúem nascido neste ano");
        }

        System.out.println("Digite um nome para busca");
        String nome = sc.nextLine();

        Optional<Pessoa> optNome = pessoas.stream()
                .filter(p -> p.getNome().toUpperCase().contains(nome.toUpperCase()))
                .findFirst();
        if(optNome.isPresent()){
            Pessoa realNome = optNome.get();
            System.out.println(realNome);
        }else{
            System.out.println("Ningúem com este nome");
        }
    }
}
