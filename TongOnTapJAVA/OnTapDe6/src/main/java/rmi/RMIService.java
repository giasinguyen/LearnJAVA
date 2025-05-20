package rmi;

import dao.BookDAO;
import dao.impl.BookDAOImpl;
import model.Book;
import service.BookService;
import service.impl.BookServiceImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMIService {
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        LocateRegistry.createRegistry(715);

        BookDAO bookDAO = new BookDAOImpl(Book.class);

        BookService bookService = new BookServiceImpl(bookDAO);

        context.bind("rmi://GiaSi:715/bookService", bookService);
        System.out.println("RMI is running!");
    }
}
