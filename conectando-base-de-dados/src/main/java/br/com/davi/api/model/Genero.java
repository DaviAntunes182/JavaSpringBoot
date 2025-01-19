package br.com.davi.api.model;

public enum Genero {
    ROMANCE("Romance", "Romance"),
    ACAO("Action", "Ação"),
    COMEDIA("Comedy", "Comedia"),
    DRAMA("Drama", "Drama"),
    CRIME("Crime", "Crime"),
    AVENTURA("Adventure", "Aventura");

    private String categoriaIngles;
    private String categoriaPortugues;

    Genero(String categoriaIngles, String categoriaPortugues) {
        this.categoriaIngles = categoriaIngles;
        this.categoriaPortugues = categoriaPortugues;
    }

    public static Genero fromString(String text){
        for (Genero genero : Genero.values()) {
            if(genero.categoriaIngles.equalsIgnoreCase(text)){
                return genero;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para os dados passados");
    }
    public static Genero fromPortugues(String text){
        for (Genero genero : Genero.values()) {
            if(genero.categoriaPortugues.equalsIgnoreCase(text)){
                return genero;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para os dados passados");
    }
}
