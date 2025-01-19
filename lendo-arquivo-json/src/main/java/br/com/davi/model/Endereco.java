package br.com.davi.model;

public class Endereco {
    private String titulo;
    private String cep;
    private String numero;
    private String complemento;

    public Endereco() {
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
}
