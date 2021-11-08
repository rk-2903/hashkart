package com.hashkart.userManagement.service.impl;

import com.hashkart.userManagement.model.User;
import com.hashkart.userManagement.repository.UserRepo;
import com.hashkart.userManagement.service.UserService;
import com.hashkart.userManagement.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordUtil passwordUtil;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User getByUserId(int userId) {
        User user = userRepo.findByUserId(userId);
        user.setPassword(passwordUtil.decrypt(user.getPassword()));
        return user;
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordUtil.encrypt(user.getPassword()));
        return userRepo.save(user);
    }
}
