package br.com.davi;

public class TestandoChars {
    public static void main(String[] args) {

        char ch1 = '{';
        int corresp1 = ch1;

        char ch2 = '}';
        int corresp2 = ch2;

        int int1 = 125;
        char char1 = (char) int1;

        char ch3 = '\'';
        int corresp3 = ch3;
        System.out.println(ch3);
        System.out.println(corresp3);

        String base = "!'[({Olá davi como vai você})]'!";

        int teste = nthIndexOf(base, ']', 1);
        System.out.println(teste);

    }
    static int nthIndexOf(String texto, String alvo, int ocorrencia){
        if(ocorrencia == 1){
            return texto.indexOf(alvo);
        }else{
            return texto.indexOf(alvo, nthIndexOf(texto, alvo, ocorrencia - 1) + alvo.length());
        }
    }
    static int nthIndexOf(String texto, char alvo, int ocorrencia){
        if(ocorrencia == 1){
            return texto.indexOf(alvo);
        }else{
            return texto.indexOf(alvo, nthIndexOf(texto, alvo, ocorrencia - 1) + 1);
            //Lógica -> Recursividade
//            indexOf(
//                      alvo,
//                      a partir da posição da indexOf(correspondencia anterior do alvo) -> chamando a recursividade sempre começaremos da primeira posição
//                      + alvo.lenght(para não pegar o próprio alvo)
//                    )
        }
    }
    static String textoEntreAspas(String base, int ocorrencia){
        int inicio = nthIndexOf(base, '"', ocorrencia);
        int fim = nthIndexOf(base, '"', ocorrencia + 1);
        return base.substring(inicio + 1, fim);
    }
    static String textoEntreColchetes(String base, int open, int close){
        int inicio = nthIndexOf(base, '[', open);
        int fim = nthIndexOf(base, ']', close);
        return base.substring(inicio + 1, fim);
    }
    static String textoEntreChaves(String base, int open, int close){
        int inicio = nthIndexOf(base, '{', open);
        int fim = nthIndexOf(base, '}', close);
        return base.substring(inicio + 1, fim);
    }
}
