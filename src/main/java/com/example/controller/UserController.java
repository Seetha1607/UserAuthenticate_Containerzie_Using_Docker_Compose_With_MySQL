/*
 *Author Name : Seetha Lakshmi.G.M
 *Date : 30-11-2022
 *Created With : IntelliJ IDEA Community Edition
 */


package com.example.controller;

import com.example.domain.User;
import com.example.exception.UserNotFoundException;
import com.example.service.IUserService;
import com.example.service.SecurityTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    private IUserService iUserService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(IUserService iUserService, SecurityTokenGenerator securityTokenGenerator) {
        this.iUserService = iUserService;
        this.securityTokenGenerator = securityTokenGenerator;
    }


    @PostMapping("/saveuser")
    public ResponseEntity<?> save(@RequestBody User user) {

        return new ResponseEntity<>(iUserService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/api/v1/fetchAllUsers")
    public ResponseEntity<?> fetch() {
        return new ResponseEntity<>(iUserService.getAllUser(), HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) throws UserNotFoundException {
        try {
            iUserService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
            Map<String, String> secretkey = new HashMap<>();
            secretkey = securityTokenGenerator.generateToken(user);
            return new ResponseEntity<>(secretkey, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            return new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/fetchUser/{id}")
    public ResponseEntity<?> fetch3(@PathVariable int id) {
        return new ResponseEntity<>(iUserService.getUserById(id), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/v1/deleteUser/{id}")
    public ResponseEntity<?> deleteFunction(@PathVariable int id) {
        iUserService.deleteUserById(id);
        return new ResponseEntity<>("User Record Deleted", HttpStatus.OK);
    }


}
