package br.com.davi;

import br.com.davi.model.Endereco;
import br.com.davi.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class GleysonSampaio {
    public static void main(String[] args) {
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(new Endereco("Casa", "22773170", "158", "Árvore Pequena"));

        Pessoa pessoa = new Pessoa("Davi");
        pessoa.addEnderecos(enderecos);


    }
}
