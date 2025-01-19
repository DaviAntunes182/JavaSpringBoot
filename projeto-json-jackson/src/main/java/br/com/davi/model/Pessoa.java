package br.com.davi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private Integer idade;
    private Genero genero;
    private List<Endereco> enderecos = new ArrayList<>();

    public Pessoa(String nome, List<Endereco> enderecos) {
        this.nome = nome;
        this.enderecos = enderecos;
    }

    public Pessoa(PessoaVo pessoaVo){
        this.nome = pessoaVo.nome();
        this.idade = pessoaVo.idade() == null ? this.idade = 0 : Integer.parseInt(pessoaVo.idade()) ;
        this.genero = Genero.fromString(pessoaVo.genero());
        addEnderecosVo(pessoaVo.enderecos());
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public Genero getGenero() {
        return genero;
    }
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    private void addEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }
    private void addEnderecos(List<Endereco> enderecos) {
        for (Endereco endereco : enderecos) {
            this.enderecos.add(endereco);
        }
    }
    private void addEnderecosVo(List<EnderecoVo> enderecosVo) {
        for (EnderecoVo enderecoVo : enderecosVo) {
            Endereco endereco = new Endereco(enderecoVo);
            addEndereco(endereco);
        }
    }

    @Override
    public String toString() {
        String msg = "";
        msg += String.format("%s   %s  \n", this.nome, this.genero);
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