package br.com.davi.model;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {
    private String nome;
    private List<Endereco> enderecos = new ArrayList<>();
    public Pessoa() {
    }

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public Pessoa(String nome, List<Endereco> enderecos) {
        this.nome = nome;
        this.enderecos = enderecos;
    }

    public String getNome() {
        return nome;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void addEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }
    public void addEnderecos(List<Endereco> enderecos) {
        for (Endereco endereco : enderecos) {
            this.enderecos.add(endereco);
        }
    }

    @Override
    public String toString() {
        String msg = "";
        msg += String.format("%s\n", this.nome);
        for (Endereco endereco : this.enderecos) {
            msg += String.format("""
                        %s  %s
                        %s
                        %s
                    """, endereco.getTitulo(), endereco.getNumero(), endereco.getCep(), endereco.getComplemento());
        }
        return msg;
    }
}