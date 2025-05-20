package service;

import dao.GenericDAO;
import model.Book;

import java.util.List;

public interface BookService extends GenericService<Book, String> {
    List<Book> istRatedBooks(String author, int rating) throws Exception;
}
