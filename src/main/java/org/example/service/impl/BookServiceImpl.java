package org.example.service.impl;

import org.example.dao.BookDao;
import org.example.dao.impl.BookDaoImpl;
import org.example.model.Book;
import org.example.service.BookService;

import java.util.List;
import java.util.Optional;

public enum BookServiceImpl implements BookService {
    INSTANCE;

    BookDao bookDao = BookDaoImpl.INSTANCE;

    @Override
    public void addBook(Book book, int userId) {
        bookDao.addBook(book, userId);
    }

    @Override
    public void removeBook(Book book, int userId) {
        bookDao.removeBook(book, userId);
    }

    @Override
    public void editBook(Book book, int userId) {
        bookDao.editBook(book, userId);
    }

    @Override
    public List<Book> getAllUserBooks(int userId) {
        return bookDao.getUserBooks(userId);
    }

    @Override
    public boolean create(Book object) {
        return false;
    }

    @Override
    public Optional<List<Book>> findAll() {
        return Optional.empty();
    }
}
