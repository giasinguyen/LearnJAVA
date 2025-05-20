package dao;

import model.Book;

import java.util.List;

public interface BookDAO extends GenericDAO<Book, String>{
//    istRatedBooks(author: String, rating: int): List<Book>
    List<Book> istRatedBooks(String author, int rating);
}

