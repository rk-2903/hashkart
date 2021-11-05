package com.hashkart.userManagement.service;

import com.hashkart.userManagement.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User getByAadhaar(String aadhaar);

    List<User> getAllUserByName(String name);

    User saveUser(User user);
}
