package br.com.davi.service;

import br.com.davi.model.Pessoa;
import br.com.davi.model.PessoaVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PessoaService {
    ObjectMapper conversor = new ObjectMapper();
    private String caminho = "src/main/resources/";
    private void habilitarDatas(){
        this.conversor.registerModule(new JavaTimeModule());
        this.conversor.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public List<PessoaVo> lerJsonDePessoa(String arquivo){
        Path origem = Paths.get(caminho + arquivo);
        File f = new File(origem.toUri());
        List<PessoaVo> pessoas = new ArrayList<>();
        try {
            if (f.exists()) {
                if (f.length() != 0) {
                    String content = Files.readString(origem);
                    if (content.indexOf("[") == 0) {
                        pessoas = lerPessoasJson(arquivo);
                    } else if (content.indexOf("{") == 0) {
                        pessoas.add(lerPessoaJson(arquivo));
                    } else {
                        System.out.println("Arquivo inválido, verifique a estrutura");
                    }
                } else {
                    System.out.println("Arquivo vazio!");
                }
            } else {
                System.out.println("Arquivo não existe");
            }
        } catch (Exception e) {
            System.out.println("O arquivo deve conter uma estrutura compatível com a classe Pessoa");
            throw new RuntimeException(e);
        }
        return pessoas;
    }

    private List<PessoaVo> lerPessoasJson(String arquivo){
        Path origem = Paths.get(caminho + arquivo);
        try {
            List<PessoaVo> pessoas = conversor.readValue(origem.toFile(), new TypeReference<List<PessoaVo>>() {});
            return pessoas;
        } catch (IOException e) {
            System.out.print("Leitura não efetuada ->");
            System.out.println("Você deve escolher um arquivo Json com todas as propriedades da classe pessoa");
            return null;
        }
    }
    private PessoaVo lerPessoaJson(String arquivo){
        Path origem = Paths.get(caminho + arquivo);
        try {
            return conversor.readValue(origem.toFile(), new TypeReference<PessoaVo>() {});
        } catch (IOException e) {
            System.out.print("Leitura não efetuada ->");
            System.out.println("Você deve escolher um arquivo Json com todas as propriedades da classe pessoa");
            return null;
        }
    }
    private void executarGravacao(List<Pessoa> pessoas,String arquivo){
        Path destino = Paths.get(caminho + arquivo);
        try {
            String json = conversor.writeValueAsString(pessoas);
            Files.createDirectories(destino.getParent());
            Files.writeString(destino, json, StandardOpenOption.CREATE);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean arquivoExiste(String arquivo){
        File file = new File(caminho + arquivo);
        return file.exists();
    }

    private void criarArquivo(String arquivo){
        File file = new File(caminho + arquivo);
        try {
            file.createNewFile();
            System.out.println("Arquivo criado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void substituirArquivo(String arquivo){
        File file = new File(caminho + arquivo);
        try {
            file.createNewFile();
            System.out.println("Arquivo substituido");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean ehJson(String arquivo){
        if(arquivo.endsWith(".json")){
            return true;
        }else{
            System.out.println("Informe um arquivo com extensão json!");
            return false;
        }
    }

    private boolean arquivoVazio(String arquivo){
        String origem = caminho + arquivo;
        File file = new File(origem);
        return file.length() <= 0;
    }
    private void gravarPessoasEmJson(List<Pessoa> pessoas, String arquivo){
        if(!arquivoExiste(arquivo) && ehJson(arquivo)){
            criarArquivo(arquivo);
            executarGravacao(pessoas, arquivo);
            relatarGravacaoArquivo(pessoas,arquivo);
        }else if (arquivoExiste(arquivo) && ehJson(arquivo)){
            if(arquivoVazio(arquivo)) {
                executarGravacao(pessoas, arquivo);
                relatarGravacaoArquivo(pessoas,arquivo);
            }else{
                Scanner sc = new Scanner(System.in);
                System.out.println("Deseja substituir o arquivo?");
                String resp = sc.nextLine().trim().substring(0, 1).toUpperCase();
                if (resp.equals("S")){
                    substituirArquivo(arquivo);
                    executarGravacao(pessoas, arquivo);
                    relatarGravacaoArquivo(pessoas,arquivo);
                }
                else{
                    System.out.println("Ok, continuando programa!");
                }
            }
        }
    }

    public void criarNovoJsonPessoas(Pessoa pessoa, String arquivo){
        habilitarDatas();
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(pessoa);
        gravarPessoasEmJson(pessoas, arquivo);
    }

    public void criarNovoJsonPessoas(List<Pessoa> pessoas, String arquivo){
        habilitarDatas();
        gravarPessoasEmJson(pessoas, arquivo);
    }

    private void adicionarAoJson(List<Pessoa> pessoasAtualizadas, String arquivo){
        if(arquivoExiste(arquivo)) {
            if(arquivoVazio(arquivo)) {
                executarGravacao(pessoasAtualizadas, arquivo);
            }
            else {
                List<Pessoa> pessoasBase = new ArrayList<>();
                for (PessoaVo pessoaVo : lerPessoasJson(arquivo)) {
                    pessoasBase.add(new Pessoa(pessoaVo));
                }
                if(pessoasBase != null){
                    try {
                        pessoasAtualizadas.addAll(pessoasBase);
                    }catch (NullPointerException e){
                        System.out.println("O arquivo não contém uma pessoa válida, escolha outro");
                    }
                    executarGravacao(pessoasAtualizadas, arquivo);
                }
            }
        }else{
            executarGravacao(pessoasAtualizadas, arquivo);
        }
    }

    public void adicionarPessoasJson(Pessoa novaPessoa, String arquivo){
        List<Pessoa> pessoasAtualizadas = new ArrayList<>();
        pessoasAtualizadas.add(novaPessoa);
        adicionarAoJson(pessoasAtualizadas, arquivo);
        System.out.println(novaPessoa.getNome() + " adicionado ao arquivo " + arquivo);
    }

    public void adicionarPessoasJson(List<Pessoa> novasPessoas, String arquivo){
        List<Pessoa> pessoasAtualizadas = new ArrayList<>(novasPessoas);
        adicionarAoJson(pessoasAtualizadas, arquivo);
        for (Pessoa pessoa : novasPessoas) {
            System.out.println(pessoa.getNome() + " adicionado ao arquivo " + arquivo);
        }
    }

    private static int nthIndexOf(String texto, char alvo, int ocorrencia){
        int result = 0;
        try {
            if(ocorrencia > 0){
                if(ocorrencia == 1){
                    result = texto.indexOf(alvo);
                }else{
                    result = texto.indexOf(alvo, nthIndexOf(texto, alvo, ocorrencia - 1) + 1);
                }
            }else {
                throw new StackOverflowError();
            }
        }catch (StackOverflowError e){
            System.out.println("A ocorrência deve existir, você deve retornar um número valido, maior que 0");
        }
        return result;
    }
    private static String textoEntre(String base, char ch, int open, int close){
        String resul;
        switch ((int)ch) {
            case '{' -> resul = base.substring(nthIndexOf(base, '{', open) + 1, nthIndexOf(base, '}', close));
            case '[' -> resul = base.substring(nthIndexOf(base, '[', open) + 1, nthIndexOf(base, ']', close));
            case '(' -> resul = base.substring(nthIndexOf(base, '(', open) + 1, nthIndexOf(base, ')', close));
            case '"', '\'' -> resul = base.substring(nthIndexOf(base, ch, open) + 1, nthIndexOf(base, ch, close));
            default -> resul = "Este char não delimita nada";
        }
        return resul;
    }
    private void relatarGravacaoArquivo(List<Pessoa> pessoas, String arquivo){
        for (Pessoa pessoa : pessoas) {
            System.out.println(pessoa.getNome() + " foi gravado no arquivo " + arquivo);
        }
    }
}
