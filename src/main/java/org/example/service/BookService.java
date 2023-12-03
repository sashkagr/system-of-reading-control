package org.example.service;

import org.example.model.Book;

import java.util.List;

public interface BookService extends Service<Book, Book>{
    void addBook(Book book, int userId);
    void removeBook(Book book, int userId);

    void editBook(Book book, int userId);
    List<Book> getAllUserBooks(int userId);
}
