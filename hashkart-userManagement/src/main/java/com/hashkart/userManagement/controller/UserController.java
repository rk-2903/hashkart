package com.hashkart.userManagement.controller;

import com.hashkart.userManagement.model.User;
import com.hashkart.userManagement.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable int userId ) {
        return ResponseEntity.ok().body(userService.getByUserId(userId));
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

}
