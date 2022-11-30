package com.example.service;

import com.example.domain.User;
import com.example.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User saveUser(User user);

    List<User> getAllUser();

    Optional<User> getUserById(int id);

    User getUserByUsernameAndPassword(String username, String password) throws UserNotFoundException;

    boolean deleteUserById(int uid);

}
