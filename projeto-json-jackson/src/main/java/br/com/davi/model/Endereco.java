package br.com.davi.model;

import java.io.Serializable;

public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;
    private String titulo;
    private String cep;
    private String numero;
    private String complemento;

    public Endereco(EnderecoVo enderecoVo) {
        this.titulo = enderecoVo.titulo();
        this.cep = enderecoVo.cep();
        this.numero = enderecoVo.numero();
        this.complemento = enderecoVo.complemento();
    }

    public Endereco(String titulo, String cep, String numero, String complemento) {
        this.titulo = titulo;
        this.cep = cep;
        this.numero = numero;
        this.complemento = complemento;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCep() {
        return cep;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

//    @Override
//    public String toString() {
//        return String.format("""
//                Endereço: %s %s
//                Cep: %s
//                %s""", this.titulo, this.numero, this.cep, this.complemento);
//    }
}
