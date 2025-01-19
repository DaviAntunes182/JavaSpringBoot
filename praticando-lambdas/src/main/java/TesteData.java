import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TesteData {
    public static void main(String[] args) {
        LocalDate data;

        try{
            //aceita
            data = LocalDate.parse("1995-10-25");
            //recusa
            data = LocalDate.parse("19951025");
        }catch (DateTimeParseException ex){
            data  = null;
        }

        System.out.println(data);
    }
}
