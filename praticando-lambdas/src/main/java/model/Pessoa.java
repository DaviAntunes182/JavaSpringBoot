package model;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Pessoa {
    private String nome;
    private Double altura;
    private Integer idade;
    private LocalDate nascimento;

    public Pessoa(String nome, Double altura, String nascimento) {
        this.nome = nome;
        this.altura = altura;
        try {
            this.nascimento = LocalDate.parse(nascimento);
            this.idade = Period.between(this.nascimento, LocalDate.now()).getYears();
        }
        catch (DateTimeParseException ex){
            this.nascimento = null;
            this.idade = null;
        }
    }

    public String getNome() {
        return nome;
    }

    public Double getAltura() {
        return altura;
    }

    public Integer getIdade() {
        return idade;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public String toString() {
        return String.format("""
                %s %.2f %s""", this.getNome(), this.getAltura(), this.getNascimento() == null ? 0 : this.getNascimento().format(formatador));
    }
}
