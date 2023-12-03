package org.example.dao.impl;

import org.example.dao.BookDao;
import org.example.model.Book;
import org.example.model.User;
import org.example.pool.ConnectionPool;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public enum BookDaoImpl implements BookDao {
    INSTANCE;

    private static final String ADD_BOOK_QUERY = "INSERT INTO books (book_key, title, description, cover, author, user_id) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String REMOVE_BOOK_QUERY = "DELETE FROM books WHERE book_key=? AND user_id=?";

    private static final String GET_ALL_USER_BOOKS = "SELECT book_key, title, description, cover, author, user_id, is_finished, start_date, end_date FROM books WHERE user_id=?";

    private static final String EDIT_BOOK = "UPDATE books SET is_finished=?, start_date=?, end_date=? WHERE book_key=? AND user_id=?";

    @Override
    public void addBook(Book book, int userId) {
        try (final Connection connection = ConnectionPool.INSTANCE.fetchConnection();
             final PreparedStatement statement = connection.prepareStatement(ADD_BOOK_QUERY)) {
            statement.setString(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getDescription());
            statement.setString(4, book.getCover());
            statement.setString(5, book.getAuthor());
            statement.setInt(6, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeBook(Book book, int userId) {
        try (final Connection connection = ConnectionPool.INSTANCE.fetchConnection();
             final PreparedStatement statement = connection.prepareStatement(REMOVE_BOOK_QUERY)) {
            statement.setString(1, book.getId());
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void editBook(Book book, int userId) {
        try (final Connection connection = ConnectionPool.INSTANCE.fetchConnection();
             final PreparedStatement statement = connection.prepareStatement(EDIT_BOOK)) {
            statement.setInt(1, book.isFinished() ? 1 : 0);
            statement.setDate(2, book.getStartDate());
            statement.setDate(3, book.getEndDate());
            statement.setString(4, book.getId());
            statement.setInt(5, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Book> getUserBooks(int userId) {
        List<Book> books = new ArrayList<>();
        try (final Connection connection = ConnectionPool.INSTANCE.fetchConnection();
             final PreparedStatement statement = connection.prepareStatement(GET_ALL_USER_BOOKS)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getString("book_key"));
                book.setTitle(resultSet.getString("title"));
                book.setDescription(resultSet.getString("description"));
                book.setCover(resultSet.getString("cover"));
                book.setAuthor(resultSet.getString("author"));
                book.setFinished(resultSet.getInt("is_finished") == 1);
                book.setStartDate(resultSet.getDate("start_date"));
                book.setEndDate(resultSet.getDate("end_date"));
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }

    @Override
    public boolean create(Book object) {
        return false;
    }

    @Override
    public Optional<List<Book>> findAll() {
        return Optional.empty();
    }

    @Override
    public Book update(Book object) {
        return null;
    }

    @Override
    public Book delete(Book object) throws SQLException {
        return null;
    }
}
