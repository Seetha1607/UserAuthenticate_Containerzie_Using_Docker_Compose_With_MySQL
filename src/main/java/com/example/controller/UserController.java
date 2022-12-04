/*
 *Author Name : Seetha Lakshmi.G.M
 *Date : 30-11-2022
 *Created With : IntelliJ IDEA Community Edition
 */


package com.example.controller;

import com.example.domain.User;
import com.example.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }


    @PostMapping("/saveuser")
    public ResponseEntity<?> save(@RequestBody User user) {

        return new ResponseEntity<>(iUserService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/fetchAllUsers")
    public ResponseEntity<?> fetch() {
        return new ResponseEntity<>(iUserService.getAllUser(), HttpStatus.CREATED);
    }

    @GetMapping("/fetchUser/{id}")
    public ResponseEntity<?> fetch3(@PathVariable int id) {
        return new ResponseEntity<>(iUserService.getUserById(id), HttpStatus.CREATED);
    }

    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<?> deleteFunction(@PathVariable int id) {
        iUserService.deleteUserById(id);
        return new ResponseEntity<>("User Record Deleted", HttpStatus.OK);
    }


}
