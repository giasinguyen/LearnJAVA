import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RMIClient {
    public static void main(String[] args) throws NamingException {
        Context context= new InitialContext();
        context.lookup("rmi://GiaSi:7152/QuestionDAO");
        System.out.println("Hello World");
    }
}
