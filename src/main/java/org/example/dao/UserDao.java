package org.example.dao;

import org.example.model.User;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Data access object that performs various operations with User model in database
 *
 */
public interface UserDao extends  Dao<User>{
    /**
     * Method is used to retrieve specified user from database by its login
     *
     * @param login is a login of specified user
     * @return Optional User model
     */
    Optional<User> findByLogin(String login);
}
