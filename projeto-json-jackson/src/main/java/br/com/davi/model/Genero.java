package br.com.davi.model;

public enum Genero {
    M("Masculino"),
    F("Feminino"),
    NI("Não Informado");

    private String generoCompleto;

    Genero(String generoCompleto){
        this.generoCompleto = generoCompleto;
    }

    public static Genero fromString(String text){
        for (Genero genero : Genero.values()) {
            if(genero.generoCompleto.equalsIgnoreCase(text)){
                return genero;
            }
        }
        return NI;
    }
}
