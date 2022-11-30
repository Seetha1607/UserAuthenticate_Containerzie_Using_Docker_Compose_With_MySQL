/*
 *Author Name : Seetha Lakshmi.G.M
 *Date : 30-11-2022
 *Created With : IntelliJ IDEA Community Edition
 */


package com.example.service;

import com.example.domain.User;
import com.example.exception.UserNotFoundException;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) throws UserNotFoundException {
        User userObj = userRepository.findByUsernameAndPassword(username, password);
        if (userObj == null) {
            throw new UserNotFoundException();
        }
        return userObj;
    }

    @Override
    public boolean deleteUserById(int uid) {
        userRepository.deleteById(uid);
        return true;
    }
}