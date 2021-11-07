package com.hashkart.userManagement.service.impl;

import com.hashkart.userManagement.model.User;
import com.hashkart.userManagement.repository.UserRepo;
import com.hashkart.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User getByUserId(int userId) {
        return userRepo.findByUserId(userId);
    }

    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }
}
