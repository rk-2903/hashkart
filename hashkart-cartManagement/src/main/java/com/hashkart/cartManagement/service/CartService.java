package com.hashkart.cartManagement.service;

import com.hashkart.cartManagement.model.Cart;

import java.util.List;

public interface CartService {
    String addProductInCart(int cartId, int productId, int quantity);

    List<Cart> findAll();

    Cart save(Cart cart);

    int calculateTotalAmountOfCart(int cartId);
}
