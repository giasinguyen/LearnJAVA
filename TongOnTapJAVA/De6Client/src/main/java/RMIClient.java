import service.BookService;

import javax.naming.Context;
import javax.naming.InitialContext;

public class RMIClient {
    public static void main(String[] args) throws Exception {
        Context context = new InitialContext();
        BookService bookService = (BookService) context.lookup("rmi://GiaSi:715/bookService");

        System.out.println(bookService.findById("9780132350884"));
    }
}
