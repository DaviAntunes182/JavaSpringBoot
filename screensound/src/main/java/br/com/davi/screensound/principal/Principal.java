package br.com.davi.screensound.principal;

import br.com.davi.screensound.model.Artista;
import br.com.davi.screensound.model.Musica;
import br.com.davi.screensound.model.Tipo;
import br.com.davi.screensound.repositories.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner sc = new Scanner(System.in);

    private ArtistaRepository artistaRepository;

    public Principal(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public void exibeMenu() {
        int op = -1;
        while(op != 0){
            System.out.printf("""
                    Digite uma opção:
                    1- Cadastrar Artista
                    2- Cadastrar Musica
                    3- Listar Musicas
                    4- Listar Musica por Artista
                    5- Pesquisar dados de um artista
                    
                    0- Sair
                    """);
            op = sc.nextInt();
            sc.nextLine();
            switch (op){
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    pesquisarDadosArtista();
                    break;
                case 0:
                    System.out.println("Encerrando aplicação");;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void cadastrarArtistas() {
        var cadastrarNovo = "S";

        while(cadastrarNovo.equalsIgnoreCase("s")){
            System.out.println("Informe o nome do artista: ");
            var nome = sc.nextLine();
            System.out.println("Informe o tipo do artista: (solo, dupla, banda)");
            var tipoString = sc.nextLine();

            Tipo tipoEnum = Tipo.valueOf(tipoString.toUpperCase());
            Artista artista = new Artista(nome, tipoEnum);

            artistaRepository.save(artista);

            System.out.println("Deseja continuar? [S/N]");
            cadastrarNovo = sc.nextLine();
        }
    }
    private void cadastrarMusicas() {
        System.out.println("Digite o nome do autor da música: ");
        var nome = sc.nextLine();

        Optional<Artista> artista = artistaRepository.findByNomeContainingIgnoreCase(nome);
        if(artista.isPresent()){
            System.out.println("Informe o título da música");
            var titulo = sc.nextLine();
            Musica musica = new Musica(titulo, artista.get());
            artista.get().getMusicas().add(musica);
            artistaRepository.save(artista.get());
        }else {
            System.out.println("Artista não encontrado");
        }
    }

    private void listarMusicas() {
        List<Artista> artistas = artistaRepository.findAll();
        artistas.forEach(artista ->  artista.getMusicas().forEach(System.out::println));
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Digite o nome do artista");
        String nome = sc.nextLine();
        List<Musica> musicas = artistaRepository.buscaMusicasPorArtista(nome);

        musicas.forEach(musica -> System.out.println(musica.getTitulo()));
    }

    private void pesquisarDadosArtista() {
        System.out.println("Era pra usar o chatgpt mas eu não to afim não");
    }


}
