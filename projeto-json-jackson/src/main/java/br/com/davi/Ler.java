package br.com.davi;

import br.com.davi.model.Pessoa;
import br.com.davi.model.PessoaVo;
import br.com.davi.service.PessoaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Ler {
    public static void main(String[] args) {
        PessoaService pessoaService = new PessoaService();
        List<Pessoa> pessoas = new ArrayList<>();
        List<PessoaVo> pessoasOp = pessoaService.lerJsonDePessoa("json.json");

        pessoasOp.stream().forEach(p -> pessoas.add(new Pessoa(p)));

        pessoas.stream().forEach(System.out::println);
    }
}
