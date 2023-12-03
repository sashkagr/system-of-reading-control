package org.example.dao;

import org.example.model.Book;

import java.util.List;

public interface BookDao extends Dao<Book> {
    void addBook(Book book, int userId);
    void removeBook(Book book, int userId);

    void editBook(Book book, int userId);
    List<Book> getUserBooks(int userId);
}
