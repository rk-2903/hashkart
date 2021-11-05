package com.hashkart.userManagement.repository;

import com.hashkart.userManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    List<User> findByName(String name);

    User findByAadhaar(String aadhaar);
}
