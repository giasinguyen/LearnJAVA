package service.impl;

import dao.BookDAO;
import dao.GenericDAO;
import model.Book;
import service.BookService;

import java.rmi.RemoteException;
import java.util.List;

public class BookServiceImpl extends GenericServiceImpl<Book, String> implements BookService {
    private BookDAO bookDAO;

    public BookServiceImpl(BookDAO bookDAO) throws RemoteException {
        super(bookDAO);
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> istRatedBooks(String author, int rating) throws Exception {
        return List.of();
    }
}
