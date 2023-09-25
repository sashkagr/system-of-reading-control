package org.example.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.UserDao;
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

public enum UserDaoImpl implements UserDao {
    INSTANCE;

    public static final String GET_ALL_USERS_QUERY = "select id, login, password from users";
    public static final String GET_USER_BY_LOGIN_QUERY = "select id, login, password from users where login=?";
    public static final String UPDATE_USER = "update users set login=?, password=? where id=?";
    public static final String CREATE_USER_QUERY = "insert into users (login, password) values (?, ?)";
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public boolean create(User object) {
        try (final Connection connection = ConnectionPool.INSTANCE.fetchConnection();
             final PreparedStatement statement = connection.prepareStatement(CREATE_USER_QUERY)) {
            final String login = object.getLogin();
            final String password = BCrypt.hashpw(object.getPassword(), BCrypt.gensalt());
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<List<User>> findAll() {
        List<User> users = new ArrayList<>();
        try (final Connection connection = ConnectionPool.INSTANCE.fetchConnection();
             final Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_QUERY);
            while(resultSet.next()) {
                users.add(new User(resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password")));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return Optional.of(users);
    }

    @Override
    public User update(User object) {
        try(final Connection connection = ConnectionPool.INSTANCE.fetchConnection();
            final PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, object.getLogin());
            statement.setString(2, object.getPassword());
            statement.setLong(3, object.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return object;
    }

    @Override
    public User delete(User object) {
        return null;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        User user = null;
        try (final Connection connection = ConnectionPool.INSTANCE.fetchConnection();
             final PreparedStatement statement = connection.prepareStatement(GET_USER_BY_LOGIN_QUERY)) {
            statement.setString(1, login);
            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"));
            }
        } catch(SQLException e) {
            logger.error(e.getMessage());
        }
        return Optional.ofNullable(user);
    }
}
