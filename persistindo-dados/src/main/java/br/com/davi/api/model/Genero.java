package br.com.davi.api.model;

public enum Genero {
    ROMANCE("Romance"),
    ACAO("Action"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime"),
    AVENTURA("Adventure");

    private String categoriaIngles;

    Genero(String categoriaIngles) {
        this.categoriaIngles = categoriaIngles;
    }

    public static Genero fromString(String text){
        for (Genero genero : Genero.values()) {
            if(genero.categoriaIngles.equalsIgnoreCase(text)){
                return genero;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para os dados passados");
    }
}
