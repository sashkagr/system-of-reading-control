package org.example.service.impl;

import org.example.dao.UserDao;
import org.example.dao.impl.UserDaoImpl;
import org.example.model.User;
import org.example.model.UserDto;
import org.example.service.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum UserServiceImpl implements UserService {
    INSTANCE(UserDaoImpl.INSTANCE);

    public static final String DUMMY_PASSWORD = "$2a$10$Z6P43xL3xPINRYG6pPwxxunfz53zO9jZ6gC.HDtzkQoQNXh52Prry";
    private UserDao userDao;

    UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public boolean create(UserDto object) {
        return false;
    }

    @Override
    public boolean create(User object) {
        return userDao.create(object);
    }

    @Override
    public Optional<List<UserDto>> findAll() {
        return userDao.findAll().map(
                users -> users.stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public Optional<UserDto> loginUser(String login, String password) {
        UserDto userDto = null;
        UserDaoImpl modifiedUserDao = (UserDaoImpl) userDao;
        Optional<User> user = modifiedUserDao.findByLogin(login);
        if (user.isPresent()) {
            if (BCrypt.checkpw(password, user.get().getPassword())) {
                userDto = convertToDto(user.get());
            }
        } else {
            //prevent timing attack by imitating password check
            BCrypt.checkpw(password, DUMMY_PASSWORD);
        }
        return Optional.ofNullable(userDto);
    }

    @Override
    public Optional<UserDto> findUserByLogin(String login) {
        UserDto userDto = null;
        final Optional<User> byLogin = userDao.findByLogin(login);
        if (byLogin.isPresent()) {
            userDto = convertToDto(byLogin.get());
        }
        return Optional.ofNullable(userDto);
    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getId(), user.getLogin(), user.getPassword());
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
