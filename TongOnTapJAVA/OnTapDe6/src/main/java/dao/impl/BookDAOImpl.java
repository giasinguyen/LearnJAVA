package dao.impl;

import dao.BookDAO;
import jakarta.persistence.EntityManager;
import model.Book;

import java.util.List;

public class BookDAOImpl extends GenericDAOImpl<Book, String> implements BookDAO {
    public BookDAOImpl(Class<Book> clazz) {
        super(clazz);
    }

    public BookDAOImpl(Class<Book> clazz, EntityManager em) {
        super(clazz, em);
    }

    @Override
    public List<Book> istRatedBooks(String author, int rating) {
        return List.of();
    }
}
