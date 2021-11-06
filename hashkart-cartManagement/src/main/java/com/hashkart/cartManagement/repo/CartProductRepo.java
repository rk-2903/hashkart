package com.hashkart.cartManagement.repo;

import com.hashkart.cartManagement.model.CartProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepo extends JpaRepository<CartProducts, Integer> {
}
