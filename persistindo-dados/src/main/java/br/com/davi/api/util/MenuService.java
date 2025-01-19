package br.com.davi.api.util;

import java.util.Scanner;

public class MenuService {

    private static Scanner sc = new Scanner(System.in).useDelimiter("\n");
    public void iniciar(){
        var opcao = 0;
        while (opcao != 7) {
            opcao = exibirMenu();
            try {
                switch (opcao) {
//                    case 1 -> service.listarContasAbertas();
//                    case 2 -> service.abrir();
//                    case 3 -> service.encerrar();
//                    case 4 -> service.consultarSaldo();
//                    case 5 -> service.realizarSaque();
//                    case 6 -> service.realizarDeposito();
                    case 7 -> finalizarAplic();
//                    default -> System.out.println("Digite um número entre 1 e 7");
                }
            } catch (Exception e) {
                System.out.println("Erro: " +e.getMessage());
                System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
                sc.next();
            }
            System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
            sc.next();
        }
    }

    private static int exibirMenu() {
        System.out.println("""
                BYTEBANK - ESCOLHA UMA OPÇÃO:
                1 - Cadastrar Serie
                2 - Cadastrar Temporada
                3 - Cadastrar Episódio
                4 - Consultar Serie
                5 - Consultar Temporada
                6 - Consultar Episódio
                7 - Sair
                """);
        if(sc.hasNextInt()){
            return sc.nextInt();
        }else {
            return 0;
        }
    }
    private void finalizarAplic(){
        System.out.println("Finalizando a aplicação!");
        System.exit(0);
    }
}
