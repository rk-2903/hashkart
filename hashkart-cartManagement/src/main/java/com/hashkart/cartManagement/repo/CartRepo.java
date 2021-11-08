package com.hashkart.cartManagement.repo;

import com.hashkart.cartManagement.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {

    Cart findByCartIdAndUserId(int cartId, int userId);
}
