package com.hashkart.cartManagement.service;

import com.hashkart.cartManagement.model.Cart;

import java.util.List;

public interface CartService {
    String addProductInCart(int userId, int cartId, int productId, int quantity) throws Exception;

    List<Cart> findAll();

    Cart save(Cart cart);

    int calculateTotalAmountOfCart(int cartId);

    void deleteById(int id);
}
