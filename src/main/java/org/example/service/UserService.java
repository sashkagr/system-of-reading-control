package org.example.service;

import org.example.model.User;
import org.example.model.UserDto;

import java.util.Optional;

public interface UserService extends Service<User, UserDto>{
    /**
     * Method is used to validate and create a new User in database
     *
     * @param object describes model of new user
     * @return true if committed otherwise false
     */
    boolean create(User object);

    /**
     * Method is used to validate user's login data
     *
     * @param login is a provided user's login
     * @param password a provided user's password
     * @return Optional UserDto model
     */
    Optional<UserDto> loginUser(String login, String password);

    /**
     * Method is used to retrieve and convert to DTO specified user from database by its login
     *
     * @param login is a login of specified user
     * @return Optional UserDto model
     */
    Optional<UserDto> findUserByLogin(String login);
}
