import java.util.Optional;

public class TesteOptional {
    public static void main(String[] args) {
        String nome = "Davi";
        Double doub;
        // 9.9
        Optional<Double> opDoub = Optional.ofNullable(null);
        // 0.0
        opDoub = Optional.ofNullable(0.0);
        //9.9
        opDoub = Optional.empty();

        doub = opDoub.orElse(9.9);

        try{
            doub = Double.valueOf("valor qualquer");
        }
        catch (NumberFormatException ex){
            //0.0
            doub = 0.0;
        }
        System.out.println(doub);
    }
}
