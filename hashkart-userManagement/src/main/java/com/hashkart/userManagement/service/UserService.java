package com.hashkart.userManagement.service;

import com.hashkart.userManagement.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User getByUserId(int userId);

    User saveUser(User user);
}
