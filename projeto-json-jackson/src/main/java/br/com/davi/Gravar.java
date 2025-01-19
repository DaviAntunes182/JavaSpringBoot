package br.com.davi;

import br.com.davi.model.Endereco;
import br.com.davi.model.Pessoa;
import br.com.davi.service.PessoaService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gravar {
    public static void main(String[] args) throws IOException {
        PessoaService pessoaService = new PessoaService();

        List<Endereco> enderecos1 = new ArrayList<>();
        enderecos1.add(new Endereco("Casa", "22773170", "158", "Árvore Pequena"));

        Pessoa pessoa1 = new Pessoa("Caio",enderecos1);

        List<Endereco> enderecos2 = new ArrayList<>();
        enderecos2.add(new Endereco("Trabalho", "2123120", "118", "Árvore Grande"));

        Pessoa pessoa2 = new Pessoa("Arthur", enderecos2);

        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(pessoa2);
        pessoas.add(pessoa1);

//        pessoaService.criarNovoJsonPessoas(pessoa2, "dipiridu.json");
//        pessoaService.criarNovoJsonPessoas(pessoas, "berinbau.json");

        pessoaService.criarNovoJsonPessoas(pessoa1, "json.json");
        pessoaService.adicionarPessoasJson(pessoa2, "json.json");
    }
}
